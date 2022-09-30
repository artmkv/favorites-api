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
    BEER_ALREADY_EXIST("exception.beer_exist"),
    FOOD_ALREADY_EXIST("exception.food_exist"),
    INVALID_ARGUMENT("exception.invalid_argument");

    private final String messageCode;

    ExceptionMessageCodes(String messageCode) {
        this.messageCode = messageCode;
    }
}