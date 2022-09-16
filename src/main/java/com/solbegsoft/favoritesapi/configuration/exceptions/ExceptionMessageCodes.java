package com.solbegsoft.favoritesapi.configuration.exceptions;

import lombok.Getter;

/**
 * Enum exception message
 */
@Getter
public enum ExceptionMessageCodes {

    /**
     * Exception constant to favorites-api
     */
    ENTITY_NOT_FOUND("exception.entity_not_found"),
    ENTITY_ALREADY_EXIST("exception.entity_exist");

    private String messageCode;

    ExceptionMessageCodes(String messageCode) {
        this.messageCode = messageCode;
    }
}
