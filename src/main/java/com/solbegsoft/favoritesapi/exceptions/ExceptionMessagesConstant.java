package com.solbegsoft.favoritesapi.exceptions;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Exception messages constants
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessagesConstant {

    public static final String ENTITY_NOT_FOUND = "exception.entity_not_found";
    public static final String ENTITY_ALREADY_EXIST = "exception.entity_exist";
}
