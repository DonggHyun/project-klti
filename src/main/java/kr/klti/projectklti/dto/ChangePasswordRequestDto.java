package kr.klti.projectklti.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String email;
    private String exPassword;
    private String newPassword;

    public ChangePasswordRequestDto(String email,String exPassword, String newPassword) {
        this.email = email;
        this.exPassword=exPassword;
        this.newPassword=newPassword;
    }

}
