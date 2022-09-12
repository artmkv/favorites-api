package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface FoodService
 */
public interface FoodService {

    /**
     * Get list of Favorites Food
     *
     * @param userId User ID
     * @return List of {@link FavoritesFoodDto}
     */
    List<FavoritesFoodDto> getListOfFood(UUID userId);

    /**
     * Get List of Favorites Food by String
     *
     * @param userId    USer ID
     * @param maybeFood String of Food
     * @return List of {@link FavoritesFoodDto}
     */
    List<FavoritesFoodDto> getListOfFoodByString(UUID userId, String maybeFood);

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
