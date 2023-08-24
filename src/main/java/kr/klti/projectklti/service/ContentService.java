package kr.klti.projectklti.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
/*import com.google.api.client.json.jackson2.JacksonFactory;*/
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoContentDetails;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;
import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.dto.ContentDto;
import kr.klti.projectklti.repository.ContentRepository;
import kr.klti.projectklti.util.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final GoogleOAuthService googleOAuthService;

    /* 콘텐츠 전체 목록 조회 */
    public List<ContentDto> getContentList() {
        return contentRepository.findAll()
                .stream()
                .map(ContentDto::of)
                .collect(Collectors.toList());
    }


    /* videoId로 특정 콘텐츠 조회 */
    public ContentDto getContentByContVideoId(String contVideoId) {
        return ContentDto.of(contentRepository.findContentByContVideoId(contVideoId));
    }


    /* 콘텐츠 DB에 등록 */
    public void insertContent(ContentDto content) {
        contentRepository.save(content.toContent());
    }


    /* 콘텐츠 유튜브 업로드 */
    public String uploadFile(String filePath) throws GeneralSecurityException, IOException {
        System.out.println("Content Service 입장");

        File savedFile = new File(filePath);
        InputStream videoInputStream = new FileInputStream(savedFile);

        String subject = SecurityUtil.getCurrentMemberId();
        YouTube youtubeService = googleOAuthService.getYouTubeService(subject);

        FFmpeg ffmpeg = new FFmpeg("src/main/resources/ffmpeg/bin/ffmpeg.exe");
        FFprobe ffprobe = new FFprobe("src/main/resources/ffmpeg/bin/ffprobe.exe");
        FFmpegProbeResult probeResult = ffprobe.probe(filePath);
        FFmpegStream videoStream = probeResult.getStreams().get(0);
        long durationInSeconds = Math.round(videoStream.duration); // 영상 길이
        System.out.println("동영상 길이: "+durationInSeconds);

        // 업로드할 동영상 정보 설정
        Video video = new Video();
        VideoSnippet snippet = new VideoSnippet();
        snippet.setTitle("test video");
        snippet.setDescription("test description");
        video.setSnippet(snippet);

        // 일부공개 설정
        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus("unlisted");
        video.setStatus(status);

        InputStreamContent mediaContent = new InputStreamContent("video/*", videoInputStream);

        YouTube.Videos.Insert videoInsert = youtubeService.videos()
                .insert(Arrays.asList("snippet", "status", "contentDetails"), video, mediaContent);

        // 업로드 진행상황 모니터링
        /*MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();
        uploader.setDirectUploadEnabled(false); // resumable 업로드 사용
        uploader.setProgressListener(new MediaHttpUploaderProgressListener() {
            public void progressChanged(MediaHttpUploader uploader) throws IOException {
                switch (uploader.getUploadState()) {
                    case INITIATION_STARTED:
                        System.out.println("Initiation Started");
                        break;
                    case INITIATION_COMPLETE:
                        System.out.println("Initiation Completed");
                        break;
                    case MEDIA_IN_PROGRESS:
                        System.out.println("Upload in progress: " + uploader.getProgress());
                        break;
                    case MEDIA_COMPLETE:
                        System.out.println("Upload Completed!");
                        break;
                    case NOT_STARTED:
                        System.out.println("Upload Not Started");
                        break;
                }
            }
        });*/

        // 업로드 실행
        Video returnedVideo = videoInsert.execute();
        System.out.println("Video uploaded: " + returnedVideo.toPrettyString());

        String contVideoId = returnedVideo.getId();     // video id

        // DB에 콘텐츠 데이터 저장하고 해당 데이터 리턴
        ContentDto content = ContentDto.builder()
                .contName("0824 test")
                .contDesc("0824 testtt")
                .contThumbnail(0)
                .contFile(0)
                .contVideoId(contVideoId)
                .contRuntime(durationInSeconds).build();
        insertContent(content);

        return contVideoId;
    }




    /* 동영상 파일 스토리지 저장 */
    public String saveVideoFile(MultipartFile file) throws IOException {

        String uploadDirectory = "C:/Users/files/";

        String originalName = file.getOriginalFilename();
        long now = System.currentTimeMillis();
        String fileName = now+"-"+originalName;		// 저장되는 파일 이름

        File uploadFile = new File(uploadDirectory+fileName);
        file.transferTo(uploadFile);

        // 파일 저장하고 파일명 반환
        return "C:/Users/files/"+fileName;
    }


}
