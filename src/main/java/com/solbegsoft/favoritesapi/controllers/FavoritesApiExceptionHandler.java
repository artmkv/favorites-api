package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.exceptions.AbstractEntityNotFoundException;
import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * Api Exception Handler
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class FavoritesApiExceptionHandler {


    private final MessageUtils messageUtils;

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
     * Handle {@link AbstractEntityNotFoundException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BeerEntityNotFoundException.class)
    public ResponseApi<String> handlerBeerEntityNotFoundException(BeerEntityNotFoundException e) {

        log.info("#EXCEPTION:" + messageUtils.getMessage(e.getMessageCode(), e.getObjects()));

        String message = e.getMessage();

        return new ResponseApi<>(message);
    }

    /**
     * Handle {@link RuntimeException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseApi<String> handlerRuntimeException(RuntimeException e) {

        return new ResponseApi<>(e.getMessage());
    }
}
