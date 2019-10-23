package com.alek.influentialpeople.exception.controller;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidHandler(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.FORBIDDEN.value(), ExceptionMessages.INCORRECT_DATA_MESSAGE), HttpStatus.FORBIDDEN);
    }

}
