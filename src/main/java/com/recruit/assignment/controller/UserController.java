package com.recruit.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruit.assignment.user.User;
import com.recruit.assignment.user.service.UserService;

@Controller
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService userInfoService;
	
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
	@GetMapping("/dpChk/{id}")
	public ResponseEntity<String> DuplicationCheckByuserId(@PathVariable("id") String userId) {
		return new ResponseEntity<String>(String.valueOf(userInfoService.existsById(userId)), HttpStatus.OK);
	}
	
	// 회원가입
	@ResponseBody
	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(User user) {
		return new ResponseEntity<String>(String.valueOf(userInfoService.signUp(user)), HttpStatus.OK);
	}
	
}
