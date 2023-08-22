package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProgress {

    /*학습자차시번호*/
    @Id
    @GeneratedValue
    private Long progNo;

    /*온라인강좌구분[LMB210]*/
    @Column(length = 10)
    private String lecDiv;

    /*콘텐츠재생위치(초)*/
    @Column
    private Long contFinalPoint;

    /*콘텐츠수강종료위치[MAX][b]*/
    @Column
    private Long contEndPoint;

    /*차시진도율[b/T*100]*/
    @Column
    private Long contProgress;

    /*차시고유번호*/
    @ManyToOne
    @JoinColumn(name = "lessNo")
    private Lesson lessNo;

    /*수강생번호*/
    @ManyToOne
    @JoinColumn(name = "stuNo")
    private Student stuNo;

    @Builder
    public MemberProgress(Long progNo,
                          String lecDiv,
                          Long contFinalPoint,
                          Long contEndPoint,
                          Long contProgress,
                          Lesson lessNo,
                          Student stuNo){
        this.progNo = progNo;
        this.lecDiv = lecDiv;
        this.contFinalPoint = contFinalPoint;
        this.contEndPoint = contEndPoint;
        this.contProgress = contProgress;
        this.lessNo = lessNo;
        this.stuNo = stuNo;
    }

}
