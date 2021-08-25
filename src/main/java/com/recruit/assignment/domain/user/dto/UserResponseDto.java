package com.recruit.assignment.domain.user.dto;

import com.recruit.assignment.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class UserResponseDto {

    private HttpStatus code;

    private String message;

    private User user;

    @Builder
    public UserResponseDto(HttpStatus code, String message, User user)  {
        this.code = code;
        this.message = message;
        this.user = User.builder().userId(user.getUserId()).build();
    }

}
