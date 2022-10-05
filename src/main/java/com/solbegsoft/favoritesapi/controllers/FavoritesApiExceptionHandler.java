package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import com.solbegsoft.favoritesapi.exceptions.*;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;

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
     * Handle {@link AbstractEntityException}
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
     * Handler InvocationTargetException
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvocationTargetException.class)
    public ResponseApi<String> handlerInvocationTargetException(InvocationTargetException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handler MissingRequestHeaderException
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseApi<String> handlerMissingRequestHeaderException(MissingRequestHeaderException e) {

        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handler {@link MethodArgumentNotValidException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseApi<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String messageCode = ExceptionMessageCodes.INVALID_ARGUMENT.getMessageCode();

        return new ResponseApi<>(messageUtils.getMessage(messageCode, e.getParameter().getParameterName()));
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

    /**
     * Handler {@link FoodExistsException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FoodExistsException.class)
    public ResponseApi<String> handlerFoodExistsException(FoodExistsException e) {

        String messageCode = ExceptionMessageCodes.FOOD_ALREADY_EXIST.getMessageCode();

        return new ResponseApi<>(messageUtils.getMessage(messageCode, e.getMessage()));
    }

    /**
     * Handler {@link BeerExistsException}
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BeerExistsException.class)
    public ResponseApi<String> handlerBeerExistsException(BeerExistsException e) {

        String messageCode = ExceptionMessageCodes.BEER_ALREADY_EXIST.getMessageCode();

        return new ResponseApi<>(messageUtils.getMessage(messageCode, e.getMessage()));
    }

    /**
     * Handler HttpMessageConversionException
     *
     * @param e exception
     * @return {@link ResponseApi}
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseApi<String> handlerHttpMessageConversionException(HttpMessageConversionException e) {

        return new ResponseApi<>(e.getMessage());
    }
}
