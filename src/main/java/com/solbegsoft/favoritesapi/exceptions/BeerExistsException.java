package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configurations.exceptions.ExceptionMessageCodes;

/**
 * BeerExistsException
 */
public class BeerExistsException extends AbstractEntityExistsException {
    /**
     * Array objects to inject into a message
     */
    private final transient Object[] objects;

    /**
     * Constructor
     *
     * @param messageCodes {@link ExceptionMessageCodes}
     * @param objects      objects
     */
    public BeerExistsException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}
