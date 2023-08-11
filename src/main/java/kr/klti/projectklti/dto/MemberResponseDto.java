package kr.klti.projectklti.dto;

import kr.klti.projectklti.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String email;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getUserEmail())
                .build();
    }
}
