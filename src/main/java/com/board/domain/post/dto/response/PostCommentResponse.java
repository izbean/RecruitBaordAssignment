package com.board.domain.post.dto.response;

import com.board.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
public class PostCommentResponse implements Serializable {

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
