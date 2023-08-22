package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="content")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Column(length = 400)
    private String contVideoId;

    /*콘텐츠길이*/
    @Column
    private Long contRuntime;


    @OneToOne(mappedBy = "content")
    private Lesson lesson;


    @Builder
    public Content(Long contNo, String contName, String contDesc, int contThumbnail,
                   int contFile, String contVideoId, Long contRuntime,Lesson lesson){
        this.contNo = contNo;
        this.contName = contName;
        this.contDesc = contDesc;
        this.contThumbnail = contThumbnail;
        this.contFile = contFile;
        this.contVideoId = contVideoId;
        this.contRuntime = contRuntime;
        this.lesson=lesson;
    }

}
