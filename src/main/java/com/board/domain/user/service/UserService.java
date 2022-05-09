package com.board.domain.user.service;

import java.util.ArrayList;

import com.board.domain.user.dto.UserRequestDto;
import com.board.domain.user.dto.UserResponseDto;
import com.board.domain.user.exception.UserNotFoundException;
import com.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.user.User;

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
        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
    }

}
