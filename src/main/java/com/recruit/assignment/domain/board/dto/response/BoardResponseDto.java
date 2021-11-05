package com.recruit.assignment.domain.board.dto.response;

import com.recruit.assignment.domain.user.dto.UserResponseDto;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public class BoardResponseDto implements Serializable {

    private static final long serialVersionUID = -1424317357078505726L;

    private long id;

    private String title;

    private String contents;

    private List<BoardCommentResponseDto> comments;

    private long commentCount = 0;

    private UserResponseDto createUser;

    private UserResponseDto modifiedUser;

    private LocalDateTime createDateTime;

    private LocalDateTime modifiedDateTime;

}
