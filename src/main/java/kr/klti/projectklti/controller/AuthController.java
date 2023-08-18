package kr.klti.projectklti.controller;

import kr.klti.projectklti.dto.MemberRequestDto;
import kr.klti.projectklti.dto.MemberResponseDto;
import kr.klti.projectklti.service.AuthService;
import kr.klti.projectklti.service.MemberService;
import kr.klti.projectklti.util.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        authService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }


    /* 토큰을 통해 회원 권한 조회 */
    @GetMapping("/role")
    public ResponseEntity<String> getRole(HttpServletRequest request) {
        System.out.println("AUTH CONTROLLER");
        String role = authService.getRole(request);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
        return ResponseEntity.ok((myInfoBySecurity));
    }

}
