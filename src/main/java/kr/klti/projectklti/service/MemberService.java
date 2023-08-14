package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.dto.MemberResponseDto;
import kr.klti.projectklti.repository.MemberRepository;
import kr.klti.projectklti.util.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public MemberResponseDto changeMemberNickname(String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        return MemberResponseDto.of(memberRepository.save(member));
    }
}