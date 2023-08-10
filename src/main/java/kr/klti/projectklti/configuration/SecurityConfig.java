package kr.klti.projectklti.configuration;

import kr.klti.projectklti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler ;
    @Autowired
    public SecurityConfig(UserService userService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler){
        this.userService=userService;
        this.customAuthenticationSuccessHandler=customAuthenticationSuccessHandler;
    }




    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // 프론트엔드 도메인
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

    @Bean // BCryptPasswordEncoder : 비밀번호 암호화 객체 - 비번 암호화해서 Bean에 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/join", "/static", "/api/**");
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println(2);

            http.cors().and()
                    .authorizeRequests()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/").hasRole("MEMBER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/loginview").permitAll()          // loginpage 설정
                    .loginProcessingUrl("/login")
                    .successHandler(customAuthenticationSuccessHandler)// 로그인 처리 URL
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
