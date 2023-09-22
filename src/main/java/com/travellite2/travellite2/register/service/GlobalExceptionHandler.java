package com.travellite2.travellite2.register.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            DuplicateUserNameException.class,
            DuplicateEmailException.class
    })
    public ResponseEntity<String> handleDuplicateUserNameException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Add more @ExceptionHandler methods for other custom exceptions or generic
    // exceptions if needed.

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<String> handleGenericException(Exception ex) {
    // return new ResponseEntity<>("An unexpected error occurred.",
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
