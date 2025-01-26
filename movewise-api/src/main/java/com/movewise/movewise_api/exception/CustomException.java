package com.movewise.movewise_api.exception;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private Object message;

    public CustomException(Object message, HttpStatus status) {
        super(message.toString());
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getMessageContent() {
        return message;
    }
}
