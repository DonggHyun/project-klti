package kr.klti.projectklti.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 필터 페인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true) // 컨트롤러 접근 전에 낚아챔, 특정 주소 접근 시 권한 및 인증 미리 처리
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // BCryptPasswordEncoder : 비밀번호 암호화 객체 - 비번 암호화해서 Bean에 등록
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    // css, js, img는 접근 가능
    @Override
    public void configure(WebSecurity web) throws Exception {
        web .ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//토큰 없이 요청 시 요청을 막기때문에 잠깐 비활성화
                .authorizeRequests()//antMatchers에서 설정한 경로는 권한없이 접근 가능
                .antMatchers("/loginview")
                .permitAll()
                .anyRequest().authenticated();// 그 외의 경로는 인증된 사용자만 접근 가능
    }
}
