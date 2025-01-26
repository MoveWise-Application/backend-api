package com.movewise.movewise_api.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.movewise.movewise_api.exception.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        return new ResponseEntity<>(e.getMessageContent(), e.getStatus());
    }

    // Handle Access Denied exceptions
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        String message = "Access Denied: You do not have the necessary permissions to access this resource.";
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    // Handle all other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}