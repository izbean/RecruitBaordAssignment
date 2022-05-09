package com.board.config.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "User Not Found."),
    USER_ALREADY_EXISTS_EXCEPTION(HttpStatus.CONFLICT, "User Already Exists.");

    private HttpStatus httpStatus;

    private String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
