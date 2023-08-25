package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class LectureDto {

    private Long lectNo;
    private String lectName;
    private String lectDesc;
    private String lectStartDate;
    private String lectEndDate;
    private String lectStatus;

    public Lecture toLecture(){
        return Lecture.builder()
                .lectNo(lectNo)
                .lectName(lectName)
                .lectDesc(lectDesc)
                .lectStartDate(lectStartDate)
                .lectEndDate(lectEndDate)
                .lectStatus(lectStatus)
                .build();
    }

    public static LectureDto of(Lecture lecture){
        return LectureDto.builder()
                .lectNo(lecture.getLectNo())
                .lectName(lecture.getLectName())
                .lectDesc(lecture.getLectDesc())
                .lectStartDate(lecture.getLectStartDate())
                .lectEndDate(lecture.getLectEndDate())
                .lectStatus(lecture.getLectStatus())
                .build();
    }
}
