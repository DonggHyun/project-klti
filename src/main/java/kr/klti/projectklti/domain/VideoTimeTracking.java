package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="VideoTimeTracking")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoTimeTracking {

    @Id
    @Column
    //일단 차시번호
    private Long weekNumber;

    @Column
    //정지위치 [MMSS} varchar형
    private String stopPoint;
    //추가구현예정

}
