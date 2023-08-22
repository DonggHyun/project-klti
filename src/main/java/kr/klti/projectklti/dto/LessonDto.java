package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LessonDto {

    private Long lessNo;
    private Long lessOrder;
    private String lessName;
    private Long supFileNo;
    private String lessDesc;
    private Lecture lecture;
    private Content content;

    public Lesson toLesson(){
        return Lesson.builder()
                .lessNo(lessNo)
                .lessOrder(lessOrder)
                .lessName(lessName)
                .supFileNo(supFileNo)
                .lessDesc(lessDesc)
                .lecture(lecture)
                .content(content)
                .build();
    }

}
