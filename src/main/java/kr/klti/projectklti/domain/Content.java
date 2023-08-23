package kr.klti.projectklti.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="content")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    /*콘텐츠 번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contNo;

    /*콘텐츠명*/
    @Column(length = 100, nullable = false)
    private String contName;

    /*콘텐츠설명*/
    @Column(length = 400)
    private String contDesc;

    /*썸네일*/
    @Column
    private int contThumbnail;

    /*콘텐츠파일*/
    @Column
    private int contFile;

    /*비디오ID*/
    @Column(length = 400, nullable = false)
    private String contVideoId;

    /*콘텐츠길이*/
    @Column
    private Long contRuntime;

    @OneToOne(mappedBy = "content")
    private Lesson lesson;


}
