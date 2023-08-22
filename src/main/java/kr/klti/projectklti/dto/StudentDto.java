package kr.klti.projectklti.dto;

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
    private Lecture lectNo;
    private Member memId;


    public Student toStudent(){
        return Student.builder()
                .stuNo(stuNo)
                .lectNo(lectNo)
                .memId(memId)
                .build();
    }

}
