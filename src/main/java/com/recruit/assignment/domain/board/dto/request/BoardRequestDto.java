package com.recruit.assignment.domain.board.dto.request;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardRequestDto {

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

    public Board toEntityWithUser(User user) {
        return Board.create()
                .title(title)
                .category(category)
                .contents(contents)
                .user(user)
                .build();
    }

}
