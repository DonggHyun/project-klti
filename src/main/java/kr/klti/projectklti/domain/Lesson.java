package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson {

    /*차시고유번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessNo;

    /*차시번호*/
    @Column
    private Long lessOrder;

    /*차시명*/
    @Column(length = 150)
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
    private Lecture lectNo;

    /*콘텐츠번호*/
    @OneToOne
    @JoinColumn(name = "contNo")
    private Content contNo;


    @Builder
    public Lesson(Long lessNo,
                  Long lessOrder,
                  String lessName,
                  Long supFileNo,
                  String lessDesc,
                  Lecture lectNo,
                  Content contNo){
        this.lessNo = lessNo;
        this.lessOrder = lessOrder;
        this.lessName = lessName;
        this.supFileNo = supFileNo;
        this.lessDesc = lessDesc;
        this.lectNo = lectNo;
        this.contNo = contNo;
    }
}
