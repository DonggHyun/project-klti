package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Lecture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class LectureDto {

    private Long lectNo;
    private String lectName;
    private String lectDesc;
    private String lectStartDate;
    private String lectEndDate;
    private String lectStatus;

    public LectureDto(Long lectNo,String lectName,String lectDesc,
                      String lectStartDate,String lectEndDate,String lectStatus){
        this.lectNo = lectNo;
        this.lectName=lectName;
        this.lectDesc=lectDesc;
        this.lectStartDate=lectStartDate;
        this.lectEndDate=lectEndDate;
        this.lectStatus=lectStatus;
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
