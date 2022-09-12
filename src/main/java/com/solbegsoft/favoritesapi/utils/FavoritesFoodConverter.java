package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Converter for {@link FavoritesFood} and {@link FavoritesFoodDto}
 */
@Mapper(componentModel = "spring")
public interface FavoritesFoodConverter {

    /**
     * Instance
     */
    FavoritesFoodConverter INSTANCE = Mappers.getMapper(FavoritesFoodConverter.class);

    /**
     * Convert {@link FavoritesFood} to {@link FavoritesFoodDto}
     *
     * @param favoritesFood {@link FavoritesFood}
     * @return {@link FavoritesFoodDto}
     */
    FavoritesFoodDto getDtoFromFavoritesFood(FavoritesFood favoritesFood);

    /**
     * Convert {@link FavoritesFoodDto} to {@link FavoritesFood}
     *
     * @param favoritesFoodDto {@link @FavoritesF}
     * @return {@link FavoritesFood}
     */
    FavoritesFood getFavoritesFoodFromDto(FavoritesFoodDto favoritesFoodDto);
}
