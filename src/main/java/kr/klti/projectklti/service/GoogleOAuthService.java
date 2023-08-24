package kr.klti.projectklti.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
/*import com.google.api.client.json.jackson2.JacksonFactory;*/
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import kr.klti.projectklti.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoogleOAuthService implements ResourceLoaderAware {

    /**
     * 유튜브 업로드 API URI
     */
    String YT_UPLOAD_URI = "https://www.googleapis.com/auth/youtube.upload";
    String YT_API_URI = "https://www.googleapis.com/auth/youtube";

    /**
     * 구글 사용자 인증 정보 attribute 키
     */
    String USER_CREDENTIAL = "GOOGLE_USER_CREDENTIAL";

    /**
     * 사용이 인가된 API uri 키
     */
    String GRANTED_SCOPES = "GOOGLE_GRANDTED_SCOPES";

    /**
     * 구글 클라이언트 ID attribute 키
     */
    String CLIENT_ID = "1035882528994-u5k44rj1aq4lt2gfq3b47jglivlligkp.apps.googleusercontent.com";

    /**
     * 구글 redirect-uri attribute 키
     */
    String REDIRECT_URI = "http://localhost:8080";

    /**
     * client-secret.path 파일 리소스
     */
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final JsonFactory JSON_FACTORY = new GsonFactory();


    private GoogleClientSecrets clientSecrets;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<String> serviceScopes;
    private String credentialPath, host;


    /* 구글 액세스 토큰을 redis에 저장하기 위함 */
    private final RedisTemplate<String, String> redisTemplate;

    private final TokenProvider tokenProvider;


    String getClientId() {
        return clientSecrets.getWeb().getClientId();
    }

    String getRedirectUri() {
        List<String> redirectUris = clientSecrets.getWeb().getRedirectUris();
        String redirectUri = redirectUris.stream()
                .filter(s -> s.contains(this.host))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("client_secret.json에서 적절한 redirectUri를 찾을 수 없음"));
        return redirectUri;
    }

/*


    *//**
     * 인증 정보 저장소에서 API 인증 정보를 가져온다. (세션 키 값 이용)
     * @return 인증 정보 (없을 시 null)
     *//*
    Credential getUserCredential(String scope) {
        return getUserCredential(Collections.singletonList(scope));
    }

    *//**
     * 인증 정보 저장소에서 API 인증 정보를 가져온다. (세션 키 값 이용)
     * @return 인증 정보 (없을 시 null)
     *//*
    public Credential getUserCredential(Collection<String> scope) {
        final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");
        final String SSN_KEY = session.getId();
        return this.getUserCredential(SSN_KEY, scope);
    }

    *//**
     * 인증 정보 저장소에서 API 인증 정보를 가져온다.
     * @param user 사용자 식별 키
     * @return 인증 정보 (없을 시 null)
     *//*
    public Credential getUserCredential(String user, Collection<String> scope) {
        final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");
        Credential credential = (Credential) session.getAttribute(USER_CREDENTIAL);

        if (credential != null
                && isGrantedUri(scope)
                && (credential.getExpiresInSeconds() == null
                || credential.getExpiresInSeconds() > 60)) {
            return credential;
        }

        return null;
    }

    boolean isGrantedUri(String scope) {
        return getScopes().contains(scope);
    }
    boolean isGrantedUri(Collection<String> scope) {
        return getScopes().containsAll(scope);
    }

    */

    /**
     * 사용자가 직접 받은 인증 코드를 인증 정보 저장소에 저장한다.
     * @param authCode 구글 API 서버로부터 받은 인증코드
     * //@param scopes 구글 API 서버로부터 받은 API Scope
     * @return 인증 정보
     */
/*    public Credential createAndStoreCredential(String authCode, Collection<String> scopes) throws IOException {
        //final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");

        try {
            GoogleAuthorizationCodeFlow authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, this.serviceScopes)
                    .build();

            // 없을 시 인증코드로부터 엑세스 토큰 발급받기
            GoogleTokenResponse googleTokenResponse = authorizationCodeFlow
                    .newTokenRequest(authCode)
                    .setRedirectUri(getRedirectUri())
                    .execute();


            //Credential credential = authorizationCodeFlow.createAndStoreCredential(googleTokenResponse, session.getId());

            // 세션에 저장해봤자 소용이 없을텐데... 수정해보자
            //session.setAttribute(USER_CREDENTIAL, credential);
            //session.setAttribute(GRANTED_SCOPES, new HashSet<>(scopes));

            //logger.debug("USER_CREDENTIAL: {}", session.getAttribute(USER_CREDENTIAL));
            //logger.debug("GRANTED_SCOPES: {}", session.getAttribute(GRANTED_SCOPES));

            // JWT 토큰에서 사용자정보를 가져와서 구글액세스토큰의 식별자로 사용
            //String key = "googleAccessToken:" + tokenProvider.getSubject("ㄴㅇㄴ");
            // Redis 에 토큰과 만료시간 저장
            //redisTemplate.opsForValue().set(key, credential.getAccessToken(), expiresIn, TimeUnit.SECONDS);


            return credential;
        }catch (TokenResponseException e){
            logger.error("구글 API 오류", e);
            throw e;
        }catch (IOException e){
            logger.error("인증코드에서 엑세스 토큰 받아오기 실패", e);
            throw new RuntimeException(e);
        }
    }*/

    /* 구글 로그인 후 들어온 code 값으로 구글액세스토큰 발급받기 */
    public GoogleTokenResponse createGoogleAccessToken(String authCode) throws IOException {
        //final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");

        try {
            GoogleAuthorizationCodeFlow authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, this.serviceScopes)
                    .build();

            GoogleTokenResponse googleTokenResponse = authorizationCodeFlow
                    .newTokenRequest(authCode)
                    .setRedirectUri(getRedirectUri())
                    .execute();
            // url 일단 하드코딩으로 넣어봄

            // scope 관련 내용 추후 추가 필요

            return googleTokenResponse;
        }catch (TokenResponseException e){
            logger.error("구글 API 오류", e);
            throw e;
        }catch (IOException e){
            logger.error("인증코드에서 엑세스 토큰 받아오기 실패", e);
            throw new RuntimeException(e);
        }
    }


    /* 구글액세스토큰 & 사용자 식별자 함께 Redis에 저장 */
    public ResponseEntity<String> saveGoogleAccessToken(GoogleTokenResponse googleAccessToken, String jwtToken) throws Exception {

        try {
            System.out.println("SERVICE saveAccessToken 입장");

            GoogleAuthorizationCodeFlow authorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, this.serviceScopes)
                    .build();

            Credential credential = authorizationCodeFlow.createAndStoreCredential(googleAccessToken, tokenProvider.getSubject(jwtToken));

            // scope 관련 내용 추후 추가 필요

            // JWT 토큰에서 가져온 사용자정보를 구글액세스토큰의 식별자로 사용
            String key = "googleAccessToken:" + tokenProvider.getSubject(jwtToken);
            // Redis에 구글토큰 정보 저장
            redisTemplate.opsForValue().set(key, credential.getAccessToken(), googleAccessToken.getExpiresInSeconds(), TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new Exception(e);
        }

        return ResponseEntity.ok("Google Access Token Saved");
    }

    /* 특정 사용자의 식별자로 구글액세스토큰 얻어오기 */
    public String getGoogleAccessToken(String subject) {
        String key = "googleAccessToken:" + subject;
        return redisTemplate.opsForValue().get(key);
    }

    /* 특정 사용자의 식별자로 구글액세스토큰 삭제하기 */
    public void deleteGoogleAccessToken(String subject) {
        String key = "googleAccessToken:" + subject;
        redisTemplate.delete(key);
    }

    /* 구글액세스토큰으로 Credential 객체 생성 */
    public static Credential createCredentialFromAccessToken(String accessToken) {
        GoogleCredential credential = new GoogleCredential();
        credential.setAccessToken(accessToken);
        return credential;
    }

    /* 구글액세스토큰으로 유튜브 서비스 초기화 */
    public YouTube getYouTubeService(String subject) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

        Credential credential = createCredentialFromAccessToken(getGoogleAccessToken(subject));

        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("klti")
                .build();
    }




    /*
    *//**
     * 서비스 계정 정보를 가져온다.
     *//*
    Credential getServiceCredential() {
        try {
            return GoogleCredential.fromStream(Files.newInputStream(Paths.get(credentialPath)))
                    .createScoped(serviceScopes);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

*/
    // 인스턴스 생성 이후 실행됨
    @PostConstruct
    public void init(){
        URL clientSecretUrl = this.getClass().getClassLoader().getResource("oauth/client_secret.json");
        Objects.requireNonNull(clientSecretUrl, "classpath:oauth/client_secret.json 파일이 없습니다.");

        try(Reader reader =  new FileReader(Paths.get(clientSecretUrl.toURI()).toFile())){
            // Client secret
            this.clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);

            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                    || clientSecrets.getDetails().getClientSecret().startsWith("Enter")) {
                String msg =  "https://console.developers.google.com/project/_/apiui/credential 에서 Client ID와 secret를 찾아서"
                        + "src/main/resources/json/client_secrets.json 에 넣어주세요";
                logger.error(msg);
                throw new IOException(msg);
            }

            // API Scope (기본값)
            serviceScopes = Arrays.asList("https://www.googleapis.com/auth/youtube.upload");
            //credentialPath = clientSecretResource;
            host = "http://localhost:3000/auth/googleoauth";
            // 일단 하드코딩으로 넣어봄
/*
            serviceScopes = Arrays.stream(propertyService.getStringArray("youtube.serviceScopes")).collect(Collectors.toList());
            credentialPath = servletContext.getRealPath(propertyService.getString("youtube.credentialPath"));
            host = propertyService.getString("host");
*/

        }catch (IOException | URISyntaxException e){
            // 서비스 초기화 실패하면 web app이 실행되어서는 안됨
            throw new RuntimeException(e);
        }
    }

/*
    private Set<String> getScopes(){
        final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");
        Set<String> grantedScopes = (Set<String>) session.getAttribute(GRANTED_SCOPES);
        return grantedScopes != null ? grantedScopes : Collections.emptySet();
    }*/


}
