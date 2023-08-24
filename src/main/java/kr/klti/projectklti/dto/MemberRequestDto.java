package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class MemberRequestDto {
    private Long memId;
    private String userId;
    private String password;
    private String name;
    private String birth;
    private String gender;
    private String userEmail;
    private String changePassword;
    private String pwYN;
    private int pwErrCnt;
    private LocalDateTime lastLoginDate;
    private String createReq;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memId(memId)
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .birth(birth)
                .gender(gender)
                .userEmail(userEmail)
                .changePassword(changePassword)
                .pwYN(pwYN)
                .pwErrCnt(pwErrCnt)
                .lastLoginDate(LocalDateTime.now())
                .createReq(createReq)
                .role(Role.MEMBER)
                .build();
    }

    // 사용자 인증 정보를 생성하고 제공
    public UsernamePasswordAuthenticationToken toAuthentication() {
        //lastLoginDate = LocalDateTime.now();
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
