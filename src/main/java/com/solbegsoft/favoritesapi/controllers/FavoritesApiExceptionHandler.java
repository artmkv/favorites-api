package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.exceptions.AbstractEntityNotFoundException;
import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.exceptions.FoodEntityNotFoundException;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;

/**
 * Api Exception Handler
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class FavoritesApiExceptionHandler {

    /**
     * @see MessageUtils
     */
    private final MessageUtils messageUtils;

    /**
     * Handle {@link Exception}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FoodEntityNotFoundException.class)
    public ResponseApi<String> handlerFoodNotFoundException(FoodEntityNotFoundException e) {

        String message = messageUtils.getMessage(e.getMessageCode(), e.getObjects());
        log.info("#EXCEPTION:" + message);

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

        String message = messageUtils.getMessage(e.getMessageCode(), e.getObjects());
        log.info("#EXCEPTION:" + message);

        return new ResponseApi<>(message);
    }

    /**
     * Handle {@link IllegalArgumentException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseApi<String> handlerIllegalArgumentException(IllegalArgumentException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handle {@link ConstraintViolationException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseApi<String> handlerConstraintViolationException(ConstraintViolationException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handle {@link HttpClientErrorException.BadRequest}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseApi<String> handlerConstraintViolationException(HttpClientErrorException.BadRequest e) {

        return new ResponseApi<>(e.getMessage());
    }
}
