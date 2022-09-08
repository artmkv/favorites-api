package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface FoodService
 */
public interface FoodService {

    List<FavoritesFoodDto> getListOfFood(UUID userId);

    List<FavoritesFoodDto> getListOfFoodByString(UUID userId, String maybeFood);

    FavoritesFoodDto saveOneFavoritesFood(FavoritesFoodDto dto);

    void deleteFavoritesFood(UUID userId, UUID id);
}
