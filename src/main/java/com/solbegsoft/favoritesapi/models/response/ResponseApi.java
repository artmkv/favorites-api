package com.solbegsoft.favoritesapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Response message in Api
 */
@Data
@AllArgsConstructor
public class ResponseApi<T> {

    /**
     * Data
     */
    private T data;
}
