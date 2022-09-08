package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFoodRequest;

import java.util.UUID;

public class RequestFoodConverter {

    public static final RequestFoodConverter INSTANCE = new RequestFoodConverter();

    public FavoritesFoodDto getFoodDtoFromRequest(UUID userId, Long foreignBeerApiId, SaveFoodRequest request){

        return FavoritesFoodDto.builder()
                .userId(userId)
                .foreignBeerApiId(foreignBeerApiId)
                .rate(request.getRate())
                .text(request.getText())
                .build();
    }
}
