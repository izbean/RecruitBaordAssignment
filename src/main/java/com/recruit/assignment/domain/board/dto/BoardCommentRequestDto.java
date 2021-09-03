package com.recruit.assignment.domain.board.dto;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotNull;

public class BoardCommentRequestDto {

    @NotNull
    private Long boardContentsId;

    private String comment;

}
