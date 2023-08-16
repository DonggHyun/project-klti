package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.dto.MemberRequestDto;
import kr.klti.projectklti.dto.MemberResponseDto;
import kr.klti.projectklti.repository.MemberRepository;
import kr.klti.projectklti.util.jwt.JwtFilter;
import kr.klti.projectklti.util.jwt.TokenDto;
import kr.klti.projectklti.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtFilter jwtFilter;

    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if (memberRepository.existsByUserId(requestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    //TokenProvider : TokenDto와 관련된 역할을 수행한느 클래스
    //                 사용자 인증 정보를 기반으로 토큰을 생성, 관리
    //                 생성된 토큰은 'TokenDto' 형식으로 반환
    public TokenDto login(MemberRequestDto requestDto) {
        try {
            //인증에 사용되는 토큰
            UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
           Member member = requestDto.toMember(passwordEncoder);
            //인증에 성공하면 Authentication 객체에서 토큰을 담음..?
            Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

            updateMemberInfo(requestDto.getUserId(),requestDto);

            //authentication을 TokenDto로 변환
            return tokenProvider.generateTokenDto(authentication);
        }catch(AuthenticationException e){
            return handleLoginFailure(e);
        }
    }
    private TokenDto handleLoginFailure(AuthenticationException e){
        return TokenDto.builder()
                .errorMessage("로그인 실패: "+e.getMessage())
                .build();
    }

    private void updateMemberInfo(String memberId, MemberRequestDto requestDto){

        Optional<Member> member = memberRepository.findByUserId(memberId);

        System.out.println("ID: "+memberId);
        if(!member.isPresent()){
            throw new EntityNotFoundException("회원을 찾을 수 없습니다: "+memberId);
        }
        Member memberResult = member.get();
        System.out.println("memberId: "+memberResult.getUserId());
        Member updateMember = Member.builder()
                .memId(memberResult.getMemId())
                .name(memberResult.getName())
                .birth(memberResult.getBirth())
                .gender(memberResult.getGender())
                .userEmail(memberResult.getUserEmail())
                .userId(memberResult.getUserId())
                .password(memberResult.getPassword())
                .changePassword(memberResult.getChangePassword())
                .pwYN(memberResult.getPwYN())
                .pwErrCnt(memberResult.getPwErrCnt())
                .lastLoginDate(LocalDateTime.now())
                .createReq(memberResult.getCreateReq())
                .role(memberResult.getRole())
                .build();

        memberRepository.save(updateMember);
    }

    public String getRole(HttpServletRequest request) {
        Authentication auth = tokenProvider.getAuthentication(jwtFilter.resolveToken(request));
        System.out.println("AUTH SERVICE");
        System.out.println(auth.getAuthorities());
        String role = (auth.getAuthorities().toArray()[0].toString()).substring(5);
        System.out.println(role);
        return role;
    }

}
