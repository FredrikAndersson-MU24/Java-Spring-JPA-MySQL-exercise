package com.example.javaspringjpamysql;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getDetailMessageArguments(), HttpStatus.BAD_REQUEST);
    }

}
