package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    /*강좌번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectNo;

    /*온라인강좌명*/
    @Column(length = 100)
    private String lectName;

    /*온라인강좌설명*/
    @Column(length = 400)
    private String lectDesc;

    /*강좌시작일시[YYYYMMDDHHMM]*/
    @Column(length = 12)
    private String lectStartDate;

    /*강좌종료일시[YYYYMMDDHHMM]*/
    @Column(length = 12)
    private String lectEndDate;

    /*강좌진행상태*/
    @Column(length = 10)
    private String lectStatus;

    @Builder
    public Lecture(Long lectNo,
                   String lectName,
                   String lectDesc,
                   String lectStartDate,
                   String lectEndDate,
                   String lectStatus){
        this.lectNo = lectNo;
        this.lectName = lectName;
        this.lectDesc = lectDesc;
        this.lectStartDate = lectStartDate;
        this.lectEndDate = lectEndDate;
        this.lectStatus = lectStatus;
    }



}
