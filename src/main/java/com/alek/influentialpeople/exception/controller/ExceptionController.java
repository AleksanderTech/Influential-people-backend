package com.alek.influentialpeople.exception.controller;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.ExceptionResponse;
import com.alek.influentialpeople.exception.exceptions.EmptyFileException;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.exception.exceptions.StateConflictException;
import com.alek.influentialpeople.exception.exceptions.StorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

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

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> entityNotFoundHandler(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<ExceptionResponse> entityExistsHandler(EntityExistsException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({StateConflictException.class})
    public ResponseEntity<ExceptionResponse> stateConflictHandler(StateConflictException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.CONFLICT.value(), ExceptionMessages.STATE_CONFLICT_MESSAGE), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({StorageException.class})
    public ResponseEntity<ExceptionResponse> storageHandler(StorageException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ExceptionMessages.FILE_STORAGE_FAIL_MESSAGE), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmptyFileException.class})
    public ResponseEntity<ExceptionResponse> emptyFileHandler(EmptyFileException ex) {
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ExceptionMessages.EMPTY_FILE_EXCEPTION), HttpStatus.BAD_REQUEST);
    }

}
