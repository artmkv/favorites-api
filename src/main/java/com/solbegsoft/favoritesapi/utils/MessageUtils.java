package com.solbegsoft.favoritesapi.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Message utils, return localized messages
 */
@Component
@RequiredArgsConstructor
public class MessageUtils {

    /**
     * @see MessageSource
     */
    private final MessageSource messageSource;

    /**
     * Get message
     *
     * @param code code from localized resource bundle
     * @return String
     */
    public String getMessage(String code) {

        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * Get message with Objects
     *
     * @param code    Message Code
     * @param objects Objects to embed in a string
     * @return String
     */
    public String getMessage(String code, Object... objects) {

        return messageSource.getMessage(code, objects, LocaleContextHolder.getLocale());
    }
}