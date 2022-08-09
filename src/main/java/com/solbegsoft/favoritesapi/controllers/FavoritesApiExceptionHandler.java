package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.exceptions.BadRequestOrPathException;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseApi<String> handlerEntityNotFoundException(EntityNotFoundException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handle {@link org.springframework.web.client.HttpClientErrorException.BadRequest}
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestOrPathException.class)
    public ResponseApi<String> handlerBadRequestOrPathException(BadRequestOrPathException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handle {@link RuntimeException}
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseApi<String> handlerRuntimeException(RuntimeException e) {

        return new ResponseApi<>(e.getMessage());
    }
}
