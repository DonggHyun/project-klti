package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="lecture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectNo;

    @Column
    private String lectName;

    @Column
    private String lectDesc;

    @Column
    private String lectStartDate;

    @Column
    private String lectEndDate;

    @Column
    private String lectStatus;
}
