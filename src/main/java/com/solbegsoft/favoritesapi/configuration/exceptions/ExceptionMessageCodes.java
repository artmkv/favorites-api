package com.solbegsoft.favoritesapi.configuration.exceptions;


import lombok.Getter;

/**
 * Enum exception message code
 */
@Getter
public enum ExceptionMessageCodes {

    /**
     * Exception constant to favorites-api
     */
    ENTITY_NOT_FOUND("exception.entity_not_found"),
    ENTITY_ALREADY_EXIST("exception.entity_exist");

    private final String messageCode;

    ExceptionMessageCodes(String messageCode) {
        this.messageCode = messageCode;
    }
}