package com.solbegsoft.favoritesapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Exception messages constants
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessagesConstant {

    public static final String ENTITY_NOT_FOUND = "Not Found with id %s";
    public static final String ENTITY_ALREADY_EXIST = "Entity with beerId %s is already exist.";
    public static final String MISMATCH_ID_IN_REQUEST = "FavoritesBeer request model doesn't match with ID in path [path-id: %s] and [request-id: %s] ";
}
