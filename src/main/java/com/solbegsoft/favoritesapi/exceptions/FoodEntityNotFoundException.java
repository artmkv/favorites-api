package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Exception food entity not found
 */
@Getter
public class FoodEntityNotFoundException extends AbstractEntityNotFoundException {

    private final transient Object[] objects;

    public FoodEntityNotFoundException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}