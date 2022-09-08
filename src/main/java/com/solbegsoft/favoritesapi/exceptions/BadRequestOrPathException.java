package com.solbegsoft.favoritesapi.exceptions;


/**
 * Bad Request or Path Exception
 */
public class BadRequestOrPathException extends RuntimeException{

    public BadRequestOrPathException(String message) {
        super(message);
    }
}
