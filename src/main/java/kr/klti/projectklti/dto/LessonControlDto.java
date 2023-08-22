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
    private Lesson lessNo;

    public LessonControl toLessonControl(){
        return LessonControl.builder()
                .ctrlNo(ctrlNo)
                .ctrlTm(ctrlTm)
                .popupMsg(popupMsg)
                .keepAns(keepAns)
                .popupId(popupId)
                .lessNo(lessNo)
                .build();
    }
}
