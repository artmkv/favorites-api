package com.solbegsoft.favoritesapi.exceptions;

import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;

import javax.persistence.EntityExistsException;

public abstract class AbstractEntityExistsException extends EntityExistsException {

    /**
     * Message Code
     */
    private final String messageCode;

    /**Constructor
     *
     * @param messageCodes {@link ExceptionMessageCodes}
     */
    protected AbstractEntityExistsException(ExceptionMessageCodes messageCodes) {
        this.messageCode = messageCodes.getMessageCode();
    }
}
