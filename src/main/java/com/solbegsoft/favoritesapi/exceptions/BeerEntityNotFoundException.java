package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Exception beer entity not found
 */
@Getter
public class BeerEntityNotFoundException extends AbstractEntityNotFoundException {

    private final transient Object[] objects;

    public BeerEntityNotFoundException(ExceptionMessageCodes messageCodes, Object... objects) {
        super(messageCodes);
        this.objects = objects;
    }
}
