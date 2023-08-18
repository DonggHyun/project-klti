package kr.klti.projectklti.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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


    @Builder
    public Content(Long contNo, String contName, String contDesc, int contThumbnail,
                   int contFile, String contVideoId, Long contRuntime){
        this.contNo = contNo;
        this.contName = contName;
        this.contDesc = contDesc;
        this.contThumbnail = contThumbnail;
        this.contFile = contFile;
        this.contVideoId = contVideoId;
        this.contRuntime = contRuntime;
    }

}
