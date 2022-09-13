package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;

import java.util.UUID;

/**
 * Converter Request Food
 */
public class RequestFoodConverter {

    /**
     * Instance
     */
    public static final RequestFoodConverter INSTANCE = new RequestFoodConverter();

    /**
     * Convert Request parameters to {@link FavoritesFoodDto}
     *
     * @param userId  User Id
     * @param request {@link SaveFavoritesFoodRequest}
     * @return {@link FavoritesFoodDto}
     */
    public FavoritesFoodDto getFoodDtoFromRequest(UUID userId, SaveFavoritesFoodRequest request) {

        return FavoritesFoodDto.builder()
                .userId(userId)
                .foreignBeerApiId(request.getForeignBeerApiId())
                .rate(request.getRate())
                .text(request.getText())
                .build();
    }

    /**
     * Convert Request parameters to {@link FavoritesFoodDto}
     *
     * @param userId UserId
     * @param string Search string
     * @return {@link GetFoodRequestDto}
     */
    public GetFoodRequestDto convertToGetFoodRequestDto(UUID userId, String string) {
        return GetFoodRequestDto.builder()
                .userId(userId)
                .text(string)
                .build();
    }
}