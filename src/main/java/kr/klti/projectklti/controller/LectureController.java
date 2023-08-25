package kr.klti.projectklti.controller;

import io.lettuce.core.ScriptOutputType;
import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.domain.Student;
import kr.klti.projectklti.dto.LectureDto;
import kr.klti.projectklti.service.LectureService;
import kr.klti.projectklti.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<LectureDto> lectureResult = lectureService.allList();
        return ResponseEntity.ok(lectureResult);
    }

    @GetMapping("/studentLectureList")
    public ResponseEntity<List<Lecture>> findByStudent(@RequestParam("memId") Long memId) {


        //memId해당하는 student LIst가져오기
        System.out.println("출력3");
        System.out.println(memId);
        List<Student> students = studentService.findByMember(memId);
        List<Lecture> lectureList = new ArrayList<>();
        System.out.println("출력1");
        System.out.println(students.get(0).getLecture());

        //list 가져와서 순서대로 lectNo 가지고 lecture 객체 가져오기
        //가져온 lecture 객체는 lectures list 에 add해주기

        for (Student student : students) {
            System.out.println("출력2");
            System.out.println(student.getLecture());

            Lecture lecture = (Lecture) lectureService.findByLectNo(student.getLecture());

            lectureList.add(lecture);

        }

        System.out.println(lectureList.get(0).getLectName());
        //list 그대로 view페이지로 전달
        //view 페이지에서 처리


        return ResponseEntity.ok(lectureList);
    }


}
