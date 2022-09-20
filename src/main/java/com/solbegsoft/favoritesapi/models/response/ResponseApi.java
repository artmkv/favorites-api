package com.solbegsoft.favoritesapi.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response message in Api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi<T> {

    /**
     * Data
     */
    private T data;
}
