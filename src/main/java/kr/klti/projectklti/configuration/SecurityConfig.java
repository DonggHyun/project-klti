package kr.klti.projectklti.configuration;

import kr.klti.projectklti.service.MemberService;
import kr.klti.projectklti.util.jwt.JwtAccessDeniedHandler;
import kr.klti.projectklti.util.jwt.JwtAuthenticationEntryPoint;
import kr.klti.projectklti.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {

    //private final MemberService memberService;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler ;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    public SecurityConfig(//MemberService memberService,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                          TokenProvider tokenProvider,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler
                          ){
        //this.memberService=memberService;
        this.customAuthenticationSuccessHandler=customAuthenticationSuccessHandler;
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
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
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/join", "/static", "/api/auth/**");
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println(2);
/*
            http.cors().and()
                    .authorizeRequests()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/").hasRole("MEMBER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/loginview").permitAll()          // loginpage 설정
                    .loginProcessingUrl("/api/login")
                    .successHandler(customAuthenticationSuccessHandler)// 로그인 처리 URL
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/failLogin")
                .and()
                    .logout()                           // logout 지원 메소드, 기본적으로 "/logout"에 접근하면 session 제거
                    .logoutSuccessUrl("/loginview")     //로그아웃 성공시 이동 페이지
                    .invalidateHttpSession(true);


            return http.build();
 */
        http
                .csrf().disable()
                .httpBasic().disable()
                .addFilter(corsFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/api/member/**").access("hasRole('MEMBER')")
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();

    }

    /*public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }*/


}
