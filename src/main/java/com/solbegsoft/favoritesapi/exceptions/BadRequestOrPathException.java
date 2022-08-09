package com.solbegsoft.favoritesapi.exceptions;


/**
 * Bad Request or Path Exception
 */
public class BadRequestOrPathException extends RuntimeException{

    public BadRequestOrPathException() {
        super();
    }

    public BadRequestOrPathException(String message) {
        super(message);
    }

    public BadRequestOrPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestOrPathException(Throwable cause) {
        super(cause);
    }
}
