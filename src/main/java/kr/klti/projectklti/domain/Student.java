package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    
    /*수강생번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuNo;

    /*강좌번호*/
    @ManyToOne
    @JoinColumn(name = "lectNo")
    private Lecture lectNo;
    
    /*회원번호*/
    @ManyToOne
    @JoinColumn(name = "memId")
    private Member memId;
    
    @Builder
    public Student(Long stuNo,
                   Lecture lectNo,
                   Member memId){
        this.stuNo = stuNo;
        this.lectNo = lectNo;
        this.memId = memId;
    }
}
