package kr.klti.projectklti.controller;

import kr.klti.projectklti.dto.LectureDto;
import kr.klti.projectklti.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    @GetMapping
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<LectureDto> lectureResult = lectureService.allList();
        return ResponseEntity.ok(lectureResult);
    }
}
