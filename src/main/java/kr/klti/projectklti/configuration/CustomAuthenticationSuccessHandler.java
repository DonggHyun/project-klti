package kr.klti.projectklti.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Component
public class CustomAuthenticationSuccessHandler  implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        System.out.println("로그인 핸들러 입장");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String username = authentication.getName();
        String token = generateJwtToken(username);
        System.out.println("TOKEN : "+token);
        // 토큰을 response에 담아서 전송 (토큰 발급이 성공하면 클라이언트는 이 토큰을 저장하여 사용)
        response.setHeader("Authorization", "Bearer " + token);


        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin");
                return;
            }
        }

        //response.sendRedirect("/admin");
    }

    private String generateJwtToken(String username) {
        // 토큰 생성 로직
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60)) // 토큰 만료 시간을 60분으로 설정
                .signWith(SignatureAlgorithm.HS512, "SECRET_KEY") // 서명 설정 필요
                .compact();
    }
}
