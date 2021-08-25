package com.recruit.assignment.domain.board.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BoardResponseDto {

    private Response response;

    private ArrayList<BoardContent> boardContents;

    @Data
    public static class Response {
        private String code;
        private String message;

    }

    @Data
    public static class BoardContent {
        private Long boardId;
        private String title;
        private String contents;
    }
}
