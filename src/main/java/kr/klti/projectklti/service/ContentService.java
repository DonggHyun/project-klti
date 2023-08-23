package kr.klti.projectklti.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
/*import com.google.api.client.json.jackson2.JacksonFactory;*/
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.common.collect.Lists;
import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.dto.ContentDto;
import kr.klti.projectklti.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private YouTube youtube;


    private final HttpTransport httpTransport = new NetHttpTransport();
    /*private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();*/
    private final List<String> SCOPES = Lists.newArrayList(YouTubeScopes.YOUTUBE_UPLOAD);
/*

    private YouTube getYouTubeService() throws IOException {

        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(new java.io.File(System.getProperty("user.home"), ".credentials/youtube-java-quickstart"));

        // Load client secrets
        InputStream in = ContentService.class.getResourceAsStream("/oauth/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(dataStoreFactory)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");

        // Build YouTube object
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("YouTubeVideoUpload")
                .build();
    }*/


    public List<ContentDto> getContentList() {
        return contentRepository.findAll()
                .stream()
                .map(ContentDto::of)
                .collect(Collectors.toList());
    }

}
