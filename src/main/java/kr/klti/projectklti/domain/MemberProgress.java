package kr.klti.projectklti.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProgress {

    /*학습자차시번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Lesson lesson;

    /*수강생번호*/
    @ManyToOne
    @JoinColumn(name = "stuNo")
    private Student student;



}
