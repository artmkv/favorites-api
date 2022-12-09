package com.solbegsoft.favoritesapi.exceptions;


import com.solbegsoft.favoritesapi.configurations.exceptions.ExceptionMessageCodes;
import lombok.Getter;

/**
 * Abstarct Exception if Entity Not Found
 */
@Getter
public abstract class AbstractEntityException extends RuntimeException{

    /**
     * Message Code
     */
    private final String messageCode;

    /**Constructor
     *
     * @param messageCodes {@link ExceptionMessageCodes}
     */
    protected AbstractEntityException(ExceptionMessageCodes messageCodes) {
        this.messageCode = messageCodes.getMessageCode();
    }
}
