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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recruit.assignment.domain.user.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public UserResponseDto getUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty())
            throw new UserNotFoundException(userId);

        return UserResponseDto.builder()
                .userId(userOptional.get().getUserId())
                .build();
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = User.createUserBuilder()
                .userId(userRequestDto.getUserId())
                .password(new BCryptPasswordEncoder().encode(userRequestDto.getPassword()))
                .email(userRequestDto.getEmail())
                .build();

        return UserResponseDto.builder()
                .userId(userRepository.save(user).getUserId())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);

        if (user == null) throw new UsernameNotFoundException(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

}
