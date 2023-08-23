package kr.klti.projectklti.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name="lecture")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    /*강좌번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectNo;

    /*온라인강좌명*/
    @Column(length = 100, nullable = false)
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
    @Column(length = 10, nullable = false)
    private String lectStatus;


}
