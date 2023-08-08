package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Role;
import kr.klti.projectklti.domain.User;
import kr.klti.projectklti.dto.UserDto;
import kr.klti.projectklti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 필드 생성자 자동 생성
@Log4j2                 //추상화 역할 라이브러리
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity()).getMemId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByUserId(userId);
        User user = users.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        int userPwErrCnt = Integer.getInteger(String.valueOf(user.getPwErrCnt()));
        return new User(user.getMemId(), user.getRole(), user.getName(), user.getBirth(),
                user.getGender(), user.getUserEmail(), user.getChangePassword(),
                user.getPwYN(), user.getPwErrCnt(), user.getLastLoginDate(),
                user.getCreateReq(), user.getReqDiv(), user.getUserId(), user.getPassword());
    }
}
