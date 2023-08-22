package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Lesson;
import kr.klti.projectklti.domain.MemberProgress;
import kr.klti.projectklti.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberProgressDto {

    private Long progNo;
    private String lecDiv;
    private Long contFinalPoint;
    private Long contEndPoint;
    private Long contProgress;
    private Lesson lessNo;
    private Student stuNo;


    public MemberProgress toMemberProgress(){
        return MemberProgress.builder()
                .progNo(progNo)
                .lecDiv(lecDiv)
                .contFinalPoint(contFinalPoint)
                .contEndPoint(contEndPoint)
                .contProgress(contProgress)
                .lessNo(lessNo)
                .stuNo(stuNo)
                .build();
    }

}
