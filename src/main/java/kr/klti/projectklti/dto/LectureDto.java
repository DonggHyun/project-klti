package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
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

}
