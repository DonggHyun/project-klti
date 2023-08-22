package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ContentDto {

    private Long contNo;
    private String contName;
    private String contDesc;
    private int contThumbnail;
    private int contFile;
    private String contVideoId;
    private Long contRuntime;

    public Content toContent() {
        return Content.builder()
                .contNo(contNo)
                .contName(contName)
                .contDesc(contDesc)
                .contThumbnail(contThumbnail)
                .contFile(contFile)
                .contVideoId(contVideoId)
                .contRuntime(contRuntime)
                .build();
    }

    public static ContentDto of(Content content) {
        return ContentDto.builder()
                .contNo(content.getContNo())
                .contName(content.getContName())
                .contDesc(content.getContDesc())
                .contThumbnail(content.getContThumbnail())
                .contFile(content.getContFile())
                .contVideoId(content.getContVideoId())
                .contRuntime(content.getContRuntime())
                .build();
    }

}
