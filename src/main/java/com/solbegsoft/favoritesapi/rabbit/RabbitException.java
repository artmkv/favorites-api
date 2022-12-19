package com.solbegsoft.favoritesapi.rabbit;


/**
 * Rabbit Exception
 */
public class RabbitException extends RuntimeException {

    /**
     * Constructor
     *
     * @param message string message
     */
    public RabbitException(String message) {
        super(message);
    }
}