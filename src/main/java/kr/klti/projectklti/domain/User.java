package kr.klti.projectklti.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name="user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails{

    //회원 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memId;

    //신분 구분..?
    @Column
    private String role;

    //성명
    @Column(length = 30)
    private String name;

    //생년월일
    @Column(length = 8)
    private String birth;

    //성별
    @Column(length = 10)
    private String gender;

    //이메일
    @Column(name = "userEmail",length = 100)
    private String userEmail;

    //ID
    @Column(unique = true,length = 40)
    private String userId;

    //PW
    @Column(length = 1000)
    private String password;

    //비밀번호 변경 일자
    @Column
    private String changePassword;

    //비밀번호 변경 여부
    @Column(length = 1)
    private String pwYN;

    //비밀번호 오류 횟수
    @Column
    private int pwErrCnt;

    //마지막 로그인 날짜
    @LastModifiedDate
    private LocalDateTime lastLoginDate;

    //회원가입 날짜
    @Column
    private String createReq;

    //신청 내역 구분
    @Column(length = 10)
    private String reqDiv;


    @Builder
    public User(Long memId,String role,String name,String birth,
                String gender,String userEmail,String userId,String password,
                String changePassword,String pwYN,int pwErrCnt,LocalDateTime lastLoginDate,String createReq,String reqDiv){
        this.memId=memId;
        this.role=role;
        this.name=name;
        this.birth=birth;
        this.gender=gender;
        this.userEmail=userEmail;
        this.userId=userId;
        this.password= password; // password 저장 시 자동 암호화
        this.changePassword=changePassword;
        this.pwYN=pwYN;
        this.pwErrCnt=pwErrCnt;
        this.lastLoginDate=lastLoginDate;
        this.createReq=createReq;
        this.reqDiv=reqDiv;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<GrantedAuthority> roles= new HashSet<>();
        return roles;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}