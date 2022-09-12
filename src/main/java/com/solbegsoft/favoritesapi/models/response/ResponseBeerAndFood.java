package com.solbegsoft.favoritesapi.models.response;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Response favorites beer with favorites food
 */
@Data
@AllArgsConstructor
public class ResponseBeerAndFood {

    /**
     * @see FavoritesBeerDto
     */
    private FavoritesBeerDto beer;

    /**
     * List {@link FavoritesFoodDto}
     */
    private List<FavoritesFoodDto> foods;
}
