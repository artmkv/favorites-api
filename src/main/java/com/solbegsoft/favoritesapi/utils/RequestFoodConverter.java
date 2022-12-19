package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

/**
 * Converter Request Food
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestFoodConverter {

    /**
     * Convert Request parameters to {@link FavoritesFoodDto}
     *
     * @param userId  User Id
     * @param request {@link SaveFavoritesFoodRequest}
     * @return {@link FavoritesFoodDto}
     */
    public static FavoritesFoodDto getFoodDtoFromRequest(UUID userId, SaveFavoritesFoodRequest request) {

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
    public static GetFoodRequestDto convertToGetFoodRequestDto(UUID userId, String string) {
        return GetFoodRequestDto.builder()
                .userId(userId)
                .text(string)
                .build();
    }
}