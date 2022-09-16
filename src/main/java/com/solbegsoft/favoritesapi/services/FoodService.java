package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface FoodService
 */
public interface FoodService {

    /**
     * Get List of Favorites Food by Foreign Beer Id
     *
     * @param userId User ID
     * @param foreignBeerId foreignBeerId
     * @return List of {@link FavoritesFoodDto}
     */
    List<FavoritesFoodDto> getListOfFoodByBeerId(UUID userId, Long foreignBeerId);

    /**
     * Get List of Favorites Food by String
     *
     * @param dto {@link GetFoodRequestDto}
     * @return List of {@link FavoritesFoodDto}
     */
    List<FavoritesFoodDto> getListOfFoodByString(GetFoodRequestDto dto);

    /**
     * Save Favorites Food
     *
     * @param dto Model to save
     * @return {@link FavoritesFoodDto}
     */
    FavoritesFoodDto saveOneFavoritesFood(FavoritesFoodDto dto);

    /**
     * Delete Favorites Food
     *
     * @param userId User ID
     * @param id     ID of Favorites Food
     */
    void deleteFavoritesFood(UUID userId, UUID id);
}
