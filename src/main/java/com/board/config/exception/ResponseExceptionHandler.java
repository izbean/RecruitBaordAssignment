package com.board.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<Object> handlerInternalException(InternalException ex, WebRequest request) {
        return handlerInternalException(ex, ex.getHttpStatus(), ex.getErrorCode(), ex.getMessage(), ex.getDescription(), request);
    }

    private ResponseEntity<Object> handlerInternalException(Exception ex, HttpStatus httpstatus, ErrorCode errorCode, String message, Object description, WebRequest request) {
        if (description instanceof Map) {
            try {
                description = objectMapper.writeValueAsString(description);
            } catch (Exception e) {
                log.debug("Failed to serialize description.", e);
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("code", errorCode);
        body.put("message", message);
        body.put("description", description);

        HttpHeaders httpHeaders = new HttpHeaders();

        return super.handleExceptionInternal(ex, body, httpHeaders, httpstatus, request);
    }

}
