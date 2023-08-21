package kr.klti.projectklti.controller;

import kr.klti.projectklti.dto.ContentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contents")
public class ContentController {

    @GetMapping
    public ResponseEntity<List<ContentDto>> getMyMemberInfo() {
        ContentDto content = new ContentDto(1L, "testContent", "test", 1, 1, "ID", 123L);
        List<ContentDto> list = new ArrayList<>();
        list.add(content);
        return ResponseEntity.ok(list);
    }

}
