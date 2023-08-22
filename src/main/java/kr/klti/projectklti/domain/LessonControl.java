package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LessonControl {

    /*통제고유번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ctrlNo;

    /*정지위치(MMSS)*/
    @Column(length = 4)
    private String ctrlTm;

    /*문제*/
    @Column(length = 200)
    private String popupMsg;

    /*정답*/
    @Column(length = 10)
    private String keepAns;

    /*호출팝업id*/
    @Column(length = 10)
    private String popupId;

    /*차시고유번호*/
    @ManyToOne
    @JoinColumn(name = "lessNo")
    private Lesson lessNo;

    @Builder
    public LessonControl(Long ctrlNo,
                         String ctrlTm,
                         String popupMsg,
                         String keepAns,
                         String popupId,
                         Lesson lessNo){
        this.ctrlNo = ctrlNo;
        this.ctrlTm = ctrlTm;
        this.popupMsg = popupMsg;
        this.keepAns = keepAns;
        this.popupId = popupId;
        this.lessNo = lessNo;
    }




}
