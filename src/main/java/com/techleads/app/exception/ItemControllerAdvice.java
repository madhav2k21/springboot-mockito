package com.techleads.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ItemControllerAdvice {

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
