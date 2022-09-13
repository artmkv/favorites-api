package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFoodRequest;
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
     * @param request {@link SaveFoodRequest}
     * @return {@link FavoritesFoodDto}
     */
    public FavoritesFoodDto getFoodDtoFromRequest(UUID userId, SaveFoodRequest request) {

        return FavoritesFoodDto.builder()
                .userId(userId)
                .foreignBeerApiId(request.getForeignBeerApiId())
                .rate(request.getRate())
                .text(request.getText())
                .build();
    }

    public GetFoodRequestDto convertToGetFoodRequestDto(UUID userId, String string){
        return GetFoodRequestDto.builder()
                .userId(userId)
                .text(string)
                .build();
    }
}