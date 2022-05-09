package com.board.domain.post.exception;

public class PostNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 9034457143728166310L;

    public PostNotFoundException(Object postId) {
        super("게시물을 찾을 수 없습니다. 글 번호[" + postId + "]");
    }

    public PostNotFoundException() {
        super("게시물을 찾을 수 없습니다.");
    }
}
