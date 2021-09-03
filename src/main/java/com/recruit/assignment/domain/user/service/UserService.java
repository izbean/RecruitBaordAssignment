package com.recruit.assignment.domain.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.recruit.assignment.domain.user.UserRole;
import com.recruit.assignment.domain.user.dto.UserRequestDto;
import com.recruit.assignment.domain.user.dto.UserResponseDto;
import com.recruit.assignment.domain.user.exception.UserNotFoundException;
import com.recruit.assignment.utils.password.PasswordEncoderUtils;
import com.recruit.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recruit.assignment.domain.user.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // 아이디 중복 체크
    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    // 사용자 정보 조회
    public UserResponseDto getUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty())
            throw new UserNotFoundException(userId);

        return UserResponseDto.builder().user(userOptional.get()).build();
    }

    // 회원가입
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return UserResponseDto.builder()
                .code(HttpStatus.OK)
                .user(User.builder()
                        .userId(userRequestDto.getUserId())
                        .password(userRequestDto.getPassword())
                        .email(userRequestDto.getEmail())
                        .build())
                .build();
    }

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);

        if (user == null) throw new UsernameNotFoundException(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

}
