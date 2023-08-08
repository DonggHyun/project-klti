package kr.klti.projectklti.configuration;

import kr.klti.projectklti.domain.User;
import kr.klti.projectklti.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    @Autowired
    public SecurityConfig(UserService userService){
        this.userService=userService;
    }

    @Bean // BCryptPasswordEncoder : 비밀번호 암호화 객체 - 비번 암호화해서 Bean에 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/join");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println(2);

            http
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/").hasRole("MEMBER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/loginview").permitAll()          // loginpage 설정
                    .loginProcessingUrl("/login")        // 로그인 처리 URL
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/failLogin")
                .and()
                    .logout()                           // logout 지원 메소드, 기본적으로 "/logout"에 접근하면 session 제거
                    .logoutSuccessUrl("/loginview")     //로그아웃 성공시 이동 페이지
                    .invalidateHttpSession(true);

            return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


}
