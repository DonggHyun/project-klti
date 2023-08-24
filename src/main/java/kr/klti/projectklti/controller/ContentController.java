package kr.klti.projectklti.controller;


import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import kr.klti.projectklti.dto.ContentDto;
import kr.klti.projectklti.service.ContentService;
import kr.klti.projectklti.service.GoogleOAuthService;
import kr.klti.projectklti.util.YoutubeUtils;
import kr.klti.projectklti.util.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    /* 콘텐츠 목록 조회 */
    @GetMapping
    public ResponseEntity<List<ContentDto>> getMyMemberInfo() {
        //ContentDto content = new ContentDto(1L, "testContent", "test", 1, 1, "ID", 123L);
        //list.add(content);
        List<ContentDto> list = contentService.getContentList();
        return ResponseEntity.ok(list);
    }



    /* 콘텐츠 등록 */
    @PostMapping
    public ContentDto uploadFile(@RequestParam("file") MultipartFile file) throws GeneralSecurityException, IOException {
        System.out.println("Content Controller 입장");
        String filePath = contentService.saveVideoFile(file);
        String contVideoId = contentService.uploadFile(filePath);
        return contentService.getContentByContVideoId(contVideoId);
    }









}
