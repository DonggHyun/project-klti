package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    //회원 번호
    private Long memId;
    //신분 구분..?
    private String role;
    //성명
    private String name;
    //생년월일
    private String birth;
    //성별
    private String gender;
    //이메일
    private String userEmail;
    //ID
    private String userId;
    //PW
    private String password;
    //비밀번호 변경 일자
    private String changePassword;
    //비밀번호 변경 여부
    private String pwYN;
    //비밀번호 오류 횟수
    private int pwErrCnt;
    //마지막 로그인 날짜
    private LocalDateTime lastLoginDate;
    //회원가입 날짜
    private String createReq;
    //신청 내역 구분
    private String reqDiv;

    public User toEntity(){
        return User.builder()
                .memId(memId)
                .role(role)
                .name(name)
                .birth(birth)
                .gender(gender)
                .userEmail(userEmail)
                .changePassword(changePassword)
                .pwYN(pwYN)
                .pwErrCnt(pwErrCnt)
                .lastLoginDate(lastLoginDate)
                .createReq(createReq)
                .reqDiv(reqDiv)
                .userId(userId)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Long memId,String role,String name,String birth,
                String gender,String userEmail,String userId,String password,
                String changePassword,String pwYN,int pwErrCnt,LocalDateTime lastLoginDate,String createReq,String reqDiv){
        this.memId=memId;
        this.role=role;
        this.name=name;
        this.birth=birth;
        this.gender=gender;
        this.userEmail=userEmail;
        this.userId=userId;
        this.password= password;
        this.changePassword=changePassword;
        this.pwYN=pwYN;
        this.pwErrCnt=pwErrCnt;
        this.lastLoginDate=lastLoginDate;
        this.changePassword=changePassword;
        this.reqDiv=reqDiv;
    }
}
