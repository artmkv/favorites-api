package com.solbegsoft.favoritesapi.exceptions;


/**
 * Bad Request or Path Exception
 */
public class BadRequestOrPathException extends RuntimeException{

    // TODO: 05.09.2022 YAGNI!!!!!
    public BadRequestOrPathException() {
        super();
    }

    public BadRequestOrPathException(String message) {
        super(message);
    }

    // TODO: 05.09.2022 YAGNI!!!!!
    public BadRequestOrPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestOrPathException(Throwable cause) {
        super(cause);
    }
}
