package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Converter Favorites Beer
 */
@Mapper(componentModel = "spring")
public interface FavoritesBeerConverter {

    /**
     * Instance
     */
    FavoritesBeerConverter INSTANCE = Mappers.getMapper(FavoritesBeerConverter.class);

    FavoritesBeerDto toDtoFromFavoritesBeer(FavoritesBeer favoritesBeer);

    FavoritesBeer toFavoritesBeerFromDto(FavoritesBeerDto favoritesBeerDto);

}
