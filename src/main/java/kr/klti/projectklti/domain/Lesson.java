package kr.klti.projectklti.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson {

    /*차시고유번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessNo;

    /*차시번호*/
    @Column(nullable = false)
    private Long lessOrder;

    /*차시명*/
    @Column(length = 150, nullable = false)
    private String lessName;

    /*보조자료파일*/
    @Column
    private Long supFileNo;

    /*차시설명*/
    @Column(length = 1000)
    private String lessDesc;

    /*강좌번호*/
    @ManyToOne
    @JoinColumn(name = "lectNo")
    private Lecture lecture;

    /*콘텐츠번호*/
    @OneToOne
    @JoinColumn(name = "contNo")
    private Content content;



}
