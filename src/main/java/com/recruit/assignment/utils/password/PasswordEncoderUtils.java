package com.recruit.assignment.utils.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class PasswordEncoderUtils {

    public static String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}
