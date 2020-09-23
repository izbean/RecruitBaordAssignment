package com.linegames.assignment.userinfo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.linegames.assignment.userinfo.entity.UserInfo;
import com.linegames.assignment.userinfo.repository.UserInfoRepository;

import java.util.List;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	// 아이디 중복 체크
	public boolean existsById(String userId) {
		return userInfoRepository.existsById(userId);
	}
	
	// 사용자 정보 조회
	public UserInfo findByUserInfo(String userId) {
		return userInfoRepository.findByUserId(userId);
	}
	
	// 회원가입
	public boolean insertByUserInfo(UserInfo userInfo) {
		BCryptPasswordEncoder cryptEncoder = new BCryptPasswordEncoder();
		userInfo.setPassword(cryptEncoder.encode(userInfo.getPassword()));
		userInfo.setCreateDateTime(LocalDateTime.now());
		userInfo.setAuthority("ROLE_USER");
		return userInfoRepository.save(userInfo) != null ? true : false;
	}

	// 로그인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepository.findByUserId(username);
		
		if ( userInfo == null ) throw new UsernameNotFoundException(username);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userInfo.getAuthority()));
		
		return new User(userInfo.getUserId(), userInfo.getPassword(), authorities);
	}
	
}
