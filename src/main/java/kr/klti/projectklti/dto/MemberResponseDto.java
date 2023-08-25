package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResponseDto {
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


    public MemberResponseDto(Long memId, String userId,String password,String name,
                             String birth,String gender,String userEmail,
                             String changePassword,String pwYN, int pwErrCnt,
                             LocalDateTime lastLoginDate,String createReq) {

        this.memId = memId;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.userEmail = userEmail;
        this.changePassword = changePassword;
        this.pwYN = pwYN;
        this.pwErrCnt = pwErrCnt;
        this.lastLoginDate = lastLoginDate;
        this.createReq = createReq;
    }

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .memId(member.getMemId())
                .userId(member.getUserId())
                .password(member.getPassword())
                .name(member.getName())
                .birth(member.getBirth())
                .gender(member.getGender())
                .userEmail(member.getUserEmail())
                .changePassword(member.getChangePassword())
                .pwYN(member.getPwYN())
                .pwErrCnt(member.getPwErrCnt())
                .lastLoginDate(member.getLastLoginDate())
                .createReq(member.getCreateReq())
                .build();
    }
}
