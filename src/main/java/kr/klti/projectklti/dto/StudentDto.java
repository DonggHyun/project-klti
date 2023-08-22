package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long stuNo;
    private Lecture lecture;
    private Member member;


    public Student toStudent(){
        return Student.builder()
                .stuNo(stuNo)
                .lecture(lecture)
                .member(member)
                .build();
    }

    public static StudentDto of(Student student) {
        return StudentDto.builder()
                .stuNo(student.getStuNo())
                .lecture(student.getLecture())
                .member(student.getMember())
                .build();
    }

}
