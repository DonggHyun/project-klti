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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contNo;

    private String contName;
    private String contDesc;
    private int contThumbnail;
    private int contFile;
    private String contVideoId;
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
