package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Lesson;
import kr.klti.projectklti.domain.LessonControl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LessonControlDto {

    private Long ctrlNo;
    private String ctrlTm;
    private String popupMsg;
    private String keepAns;
    private String popupId;
    private Lesson lesson;

    public LessonControl toLessonControl(){
        return LessonControl.builder()
                .ctrlNo(ctrlNo)
                .ctrlTm(ctrlTm)
                .popupMsg(popupMsg)
                .keepAns(keepAns)
                .popupId(popupId)
                .lesson(lesson)
                .build();
    }

    public static LessonControlDto of(LessonControl lessonControl){
        return LessonControlDto.builder()
                .ctrlNo(lessonControl.getCtrlNo())
                .ctrlTm(lessonControl.getCtrlTm())
                .popupMsg(lessonControl.getPopupMsg())
                .keepAns(lessonControl.getKeepAns())
                .popupId(lessonControl.getPopupId())
                .lesson(lessonControl.getLesson())
                .build();
    }
}
