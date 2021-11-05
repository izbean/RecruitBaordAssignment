package com.recruit.assignment.domain.user.dto;

import com.recruit.assignment.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
public class UserResponseDto {

    private String userId;

}
