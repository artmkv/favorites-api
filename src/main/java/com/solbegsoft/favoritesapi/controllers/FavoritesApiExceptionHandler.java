package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Api Exception Handler
 */
@RestControllerAdvice
public class FavoritesApiExceptionHandler {

    /**
     * Handle {@link Exception}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ExceptionHandler(Exception.class)
    public ResponseApi<String> handlerException(Exception e) {

        return new ResponseApi<>(e.getMessage());
    }
}
