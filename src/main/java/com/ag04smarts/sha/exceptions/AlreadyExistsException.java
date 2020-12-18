package com.ag04smarts.sha.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public	AlreadyExistsException() {

    }


    public	AlreadyExistsException(String message) {

        super(message);

    }


    public	AlreadyExistsException(Throwable cause) {

        super(cause);

    }


    public	AlreadyExistsException(String message, Throwable cause) {

        super(message, cause);

    }

}
