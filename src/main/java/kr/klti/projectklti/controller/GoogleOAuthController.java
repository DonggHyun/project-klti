package kr.klti.projectklti.controller;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import kr.klti.projectklti.service.GoogleOAuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class GoogleOAuthController {

    private static final Log logger = LogFactory.getLog(GoogleOAuthController.class);
    @Resource(name = "googleOAuthService")
    private GoogleOAuthService googleOAuthService;


    GoogleTokenResponse googleAccessToken;


    /* 토큰 발급 */
    @PostMapping(value = "/api/admin/oauthcallback")
    public ResponseEntity<Map<String, Object>> callback(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Map<String, String> requestBody) {
        logger.info("Google OAUth 컨트롤러 입장");

        String authorizationCode = requestBody.get("GoogleToken");
        String scope = requestBody.get("scope");

        Map<String, Object> responseBody = null;

        try {
            // 토큰 발급
            googleAccessToken = googleOAuthService.createGoogleAccessToken(authorizationCode);

            // 생성된 토큰 정보를 Map에 담아 JSON 형식으로 응답
            responseBody = new HashMap<>();
            responseBody.put("message", "Google OAuth Token Created");
            responseBody.put("accessToken", googleAccessToken.getAccessToken());
            responseBody.put("expiresInSeconds", googleAccessToken.getExpiresInSeconds());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(responseBody);
    }

    /* 토큰 저장 */
    @PostMapping(value = "/api/admin/googleaccesstoken")
    public ResponseEntity<String> saveGoogleAccessToken(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody Map<String, String> requestBody) {
        logger.info("컨트롤러 saveGoogleAccessToken 입장");

        String jwtToken = requestBody.get("jwtToken");

        try {
            // 토큰 저장
            googleOAuthService.saveGoogleAccessToken(googleAccessToken, jwtToken);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("Google OAuth Token Saved");
    }




    /*
    @RequestMapping(value = "googleAccessToken.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getGoogleAccessToken(HttpServletResponse response , @RequestParam("scope") Collection<String> scopes) throws IOException{
        Map<String, Object> ret = new LinkedHashMap<>();
        if(scopes.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Credential credential = googleOAuthService.getUserCredential(scopes);
        if(credential == null){
            ret.put("O_RESULT", "-1");
            ret.put("O_RESULT_STR", "Google OAuth 인증이 필요합니다.");
            ret.put("O_RESULT_STR_VALUE", "");
        }else{
            ret.put("O_RESULT", "0");
            ret.put("O_RESULT_STR", "정상적으로 처리됐습니다.");
            ret.put("O_RESULT_STR_VALUE", credential.getAccessToken());
            ret.put("expireInSeconds", credential.getExpiresInSeconds());
        }
        return ret;
    }
*/


}
