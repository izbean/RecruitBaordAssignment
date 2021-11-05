package com.recruit.assignment.domain.board.dto.response;

import com.recruit.assignment.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
public class BoardCommentResponseDto implements Serializable {

    private static final long serialVersionUID = 7176321100778256501L;

    private long id;

    private String comment;

    private boolean isBlocked;

    private boolean isDeleted;

    private LocalDateTime createDate;

    private UserResponseDto createUser;

    private LocalDateTime modifiedDate;

    private UserResponseDto modifiedUser;

}
