package kr.klti.projectklti.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name="student")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    
    /*수강생번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuNo;

    /*강좌번호*/
    @ManyToOne
    @JoinColumn(name = "lectNo")
    private Lecture lecture;
    
    /*회원번호*/
    @ManyToOne
    @JoinColumn(name = "memId")
    private Member member;
    

}
