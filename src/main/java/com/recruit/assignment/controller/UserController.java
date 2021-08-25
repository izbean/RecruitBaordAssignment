package com.recruit.assignment.controller;

import com.recruit.assignment.domain.user.dto.UserRequestDto;
import com.recruit.assignment.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.domain.user.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/user/register";
	}
	
	// 아이디 중복체크
	@ResponseBody
	@GetMapping("/{id}")
	public ResponseEntity<String> DuplicationCheckByuserId(@PathVariable("id") String userId) {
		return new ResponseEntity<String>(String.valueOf(userService.existsById(userId)), HttpStatus.OK);
	}
	
	// 회원가입
	@ResponseBody
	@PostMapping
	public UserResponseDto createUser(@Valid UserRequestDto userRequestDto) {
		return userService.createUser(userRequestDto);
	}

	@ResponseBody
	@PutMapping
	public UserResponseDto updateUser(UserRequestDto userRequestDto) {
		return null;
	}
	
}
