package kr.klti.projectklti.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean // BCryptPasswordEncoder : 비밀번호 암호화 객체 - 비번 암호화해서 Bean에 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                 //antMatchers에서 설정한 경로는 권한없이 접근 가능
                    .antMatchers("/css/**","/js/**","/img/**","/","/loginview")
                    .permitAll()
                    .anyRequest().authenticated()        // 그 외의 경로는 인증된 사용자만 접근 가능
                .and()
                    .formLogin()
                    .loginPage("/loginview")             // loginpage 설정
                    .loginProcessingUrl("/login")        // 로그인 처리 URL
                    .defaultSuccessUrl("/")              // 로그인 성공시 이동할 곳
                .and()
                    .logout()                           // logout 지원 메소드, 기본적으로 "/logout"에 접근하면 session 제거
                    .logoutSuccessUrl("/loginview")     //로그아웃 성공시 이동 페이지
                    .invalidateHttpSession(true);

        return http.build();
    }

}
