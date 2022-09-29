package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Exception beer entity not found
 */
@Getter
public class BeerEntityNotFoundException extends AbstractEntityNotFoundException {

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
    public BeerEntityNotFoundException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}
