package com.recruit.assignment.config.exception;

import org.springframework.http.HttpStatus;

public abstract class InternalException extends RuntimeException {

    private static final long serialVersionUID = -3000388542519297166L;

    private Object description;

    public abstract HttpStatus getHttpStatus();

    public abstract ErrorCode getErrorCode();

    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(String message, Object description, Throwable cause) {
        super(message, cause);
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

}
