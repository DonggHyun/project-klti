package kr.klti.projectklti.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="lesson")
@Getter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessNo;

    @Column
    private int lessOrder;

    @Column
    private String lessName;

    @Column
    private int subFileNo;

    @Column(length = 1000)
    private String lessDesc;

    // 1개의 강의에 여러개의 차시 , 단방향 조인
    @ManyToOne
    @JoinColumn(name="lectNo")
    private Lecture lecture;

    @OneToOne
    @JoinColumn(name="contNo")
    private Content content;
}
