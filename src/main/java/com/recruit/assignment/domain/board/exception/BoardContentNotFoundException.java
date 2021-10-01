package com.recruit.assignment.domain.board.exception;

public class BoardContentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 9034457143728166310L;

    public BoardContentNotFoundException(Object boardId) {
        super("게시물을 찾을 수 없습니다. 글 번호[" + boardId + "]");
    }

    public BoardContentNotFoundException() {
        super("게시물을 찾을 수 없습니다.");
    }
}
