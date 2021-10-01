package com.recruit.assignment.domain.board.dto;

import com.recruit.assignment.domain.board.Board;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardCreateRequestDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String contents;

    private String userId;

    public Board toEntity() {
        return Board.create()
                .title(title)
                .category(category)
                .contents(contents)
                .build();
    }

}
