package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Abstarct Exception if Entity Not Found
 */
@Getter
public abstract class AbstractEntityNotFoundException extends RuntimeException{

    /**
     * Message Code
     */
    private final String messageCode;

    /**Constructor
     *
     * @param messageCodes {@link ExceptionMessageCodes}
     */
    protected AbstractEntityNotFoundException(ExceptionMessageCodes messageCodes) {
        this.messageCode = messageCodes.getMessageCode();
    }
}
