package com.recruit.assignment.domain.post.dto.response;

import com.recruit.assignment.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostResponse implements Serializable {

    private static final long serialVersionUID = -1424317357078505726L;

    private Long id;

    private String title;

    private String contents;

    private List<PostCommentResponse> comments;

    private long commentCount = 0;

    private UserResponseDto createUser;

    private UserResponseDto modifiedUser;

    private LocalDateTime createDateTime;

    private LocalDateTime modifiedDateTime;

}
