package com.recruit.assignment.domain.board.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String content;

}
