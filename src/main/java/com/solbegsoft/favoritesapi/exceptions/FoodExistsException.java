package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;

/**
 * FoodExistsException
 */
public class FoodExistsException extends AbstractEntityExistsException {

    /**
     * Array objects to inject into a message
     */
    private final transient Object[] objects;

    /**
     * Constructor
     *
     * @param messageCodes {@link ExceptionMessageCodes}
     * @param objects objects
     */
    public FoodExistsException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}
