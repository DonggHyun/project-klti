package kr.klti.projectklti.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class TokenDto {
    private String grantType;
    private String accessToken;
    private Long tokenExpiresIn;
    private String errorMessage;

    public TokenDto(String grantType,String accessToken, Long tokenExpiresIn
                    ,String errorMessage){
        this.grantType=grantType;
        this.accessToken=accessToken;
        this.tokenExpiresIn=tokenExpiresIn;
        this.errorMessage=errorMessage;
    }
}
