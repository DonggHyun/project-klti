package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Content;
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
    private Lesson lesson;
    private Student student;


    public MemberProgress toMemberProgress(){
        return MemberProgress.builder()
                .progNo(progNo)
                .lecDiv(lecDiv)
                .contFinalPoint(contFinalPoint)
                .contEndPoint(contEndPoint)
                .contProgress(contProgress)
                .lesson(lesson)
                .student(student)
                .build();
    }

    public static MemberProgressDto of(MemberProgress memberProgress) {
        return MemberProgressDto.builder()
                .progNo(memberProgress.getProgNo())
                .lecDiv(memberProgress.getLecDiv())
                .contFinalPoint(memberProgress.getContFinalPoint())
                .contEndPoint(memberProgress.getContEndPoint())
                .contProgress(memberProgress.getContProgress())
                .lesson(memberProgress.getLesson())
                .student(memberProgress.getStudent())
                .build();
    }

}
