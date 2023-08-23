package kr.klti.projectklti.controller;

import kr.klti.projectklti.dto.LectureDto;
import kr.klti.projectklti.dto.LessonDto;
import kr.klti.projectklti.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonDto>> findLesson(@RequestParam("lectNo") String lectNo, LectureDto lectureDto){
        Long getLectNo = Long.valueOf(lectNo);
        List<LessonDto> lessonList = lessonService.getLessonList(getLectNo);

        return ResponseEntity.ok(lessonList);
    }
}
