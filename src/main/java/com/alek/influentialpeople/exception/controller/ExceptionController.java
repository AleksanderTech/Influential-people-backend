package com.alek.influentialpeople.exception.controller;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.ExceptionResponse;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.exception.exceptions.StateConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponse> accessDeniedHandler(AccessDeniedException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.FORBIDDEN.value(), ExceptionMessages.ACCESS_DENIED_MESSAGE), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ExceptionResponse> usernameNotFoundHandler(UsernameNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ExceptionMessages.NOT_FOUND_USER_MESSAGE), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<ExceptionResponse> entityExistsHandler(EntityExistsException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.CONFLICT.value(), ExceptionMessages.USER_ALREADY_EXIST_MESSAGE), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({StateConflictException.class})
    public ResponseEntity<ExceptionResponse> stateConflictHandler(StateConflictException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.CONFLICT.value(), ExceptionMessages.STATE_CONFLICT_MESSAGE), HttpStatus.CONFLICT);
    }
}
