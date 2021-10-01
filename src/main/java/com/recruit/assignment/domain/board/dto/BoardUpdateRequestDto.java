package com.recruit.assignment.domain.board.dto;

import com.recruit.assignment.domain.board.Board;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BoardUpdateRequestDto {

    @NotNull
    private Long boardId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String contents;

    private String userId;

}
