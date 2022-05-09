package com.board.domain.user.exception;

import com.board.config.exception.ErrorCode;
import com.board.config.exception.InternalException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends InternalException {

    private static final long serialVersionUID = 5348040018743268115L;

    @Override
    public HttpStatus getHttpStatus() {
        return ErrorCode.USER_NOT_FOUND_EXCEPTION.getHttpStatus();
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_NOT_FOUND_EXCEPTION;
    }

    public UserNotFoundException() {
        super("User Not Found");
    }

    public UserNotFoundException(String userId) {
        super(userId + " Not Found");
    }

}
