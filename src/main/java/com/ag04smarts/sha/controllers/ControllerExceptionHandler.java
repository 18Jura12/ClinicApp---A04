package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.exceptions.AlreadyExistsException;
import com.ag04smarts.sha.exceptions.BadRequestException;
import com.ag04smarts.sha.exceptions.EmptyFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    ResponseEntity<Object> handleAlreadyExists(AlreadyExistsException ex) {

        log.error("Handling AlreadyExistsException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<Object> handleNullPointer(NullPointerException ex) {

        log.error("Handling NullPointerException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {

        log.error("Handling IllegalArgumentException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EmptyFileException.class)
    ResponseEntity<Object> handleEmptyFile(EmptyFileException ex) {

        log.error("Handling EmptyFileException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<Object> handleRuntime(RuntimeException ex) {

        log.error("Handling RuntimeException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {

        log.error("Handling MethodArgumentNotValidException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<Object> handleBadRequest(BadRequestException ex) {

        log.error("Handling BadRequestException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {

        log.error("Handling EntityNotFoundException: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

    }

}
