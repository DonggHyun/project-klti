package kr.klti.projectklti.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.auth.oauth2.OAuth2Credentials;
import kr.klti.projectklti.service.GoogleOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
@RequiredArgsConstructor
public class YoutubeUtils {

/*    @Autowired
    private static final GoogleOAuthService googleOAuthService;

    public static YouTube getYouTubeService() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

        Credential credential = googleOAuthService.createCredentialFromAccessToken(googleOAuthService.getGoogleAccessToken());

        return new YouTube.Builder(httpTransport, JSON_FACTORY, httpRequestInitializer)
                .setApplicationName("klti")
                .build();
    }*/


}
