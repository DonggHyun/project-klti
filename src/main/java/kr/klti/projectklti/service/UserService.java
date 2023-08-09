package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Role;
import kr.klti.projectklti.domain.User;
import kr.klti.projectklti.dto.UserDto;
import kr.klti.projectklti.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 필드 생성자 자동 생성
@EnableTransactionManagement
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public Long joinUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity()).getMemId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(1);
        Optional<User> users = userRepository.findByUserId(username);
        User user = users.orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(user.getUserId())) {
            System.out.println(2);
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        System.out.println(5);
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(), user.getPassword(), authorities
        );
    }
}
