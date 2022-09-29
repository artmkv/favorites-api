package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Exception food entity not found
 */
@Getter
public class FoodEntityNotFoundException extends AbstractEntityNotFoundException {

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
    public FoodEntityNotFoundException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}