package com.recruit.assignment.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.recruit.assignment.user.UserRole;
import com.recruit.assignment.utils.password.PasswordEncoderUtils;
import com.recruit.assignment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recruit.assignment.user.User;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	// 아이디 중복 체크
	public boolean existsById(String userId) {
		return userRepository.existsById(userId);
	}
	
	// 사용자 정보 조회
	public User findByUserInfo(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	// 회원가입
	public boolean signUp(User user) {

		if (existsById(user.getUserId()))


		user.setPassword(PasswordEncoderUtils.encryptPassword(user.getPassword()));
		user.setCreateDateTime(LocalDateTime.now());
		user.setAuthority(UserRole.USER);
		userRepository.save(user);
		return true;
	}

	// 로그인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserId(username);
		
		if ( user == null ) throw new UsernameNotFoundException(username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));
		
		return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), authorities);
	}
	
}
