package com.mb.basiclearningspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Resource not found")
                .build();
        return ResponseEntity.of(Optional.of(errorResponse));
    }
}
