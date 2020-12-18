package com.ag04smarts.sha.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyFileException extends RuntimeException {

    private static final long serialVersionUID = 2L;


    public	EmptyFileException() {

    }


    public	EmptyFileException(String message) {

        super(message);

    }


    public	EmptyFileException(Throwable cause) {

        super(cause);

    }


    public	EmptyFileException(String message, Throwable cause) {

        super(message, cause);

    }

}
