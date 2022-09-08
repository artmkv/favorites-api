package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Favorites Food Converter
 */
@Mapper(componentModel = "spring")
public interface FavoritesFoodConverter {

    FavoritesFoodConverter INSTANCE = Mappers.getMapper(FavoritesFoodConverter.class);

    FavoritesFoodDto getDtoFromFavoritesFood(FavoritesFood favoritesFood);
}
