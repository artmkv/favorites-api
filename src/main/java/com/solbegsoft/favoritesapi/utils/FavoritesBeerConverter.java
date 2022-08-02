package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Converter
 */
@Mapper(componentModel = "spring")
public interface FavoritesBeerConverter {

    FavoritesBeerDto toDto(FavoritesBeer favoritesBeer);
    FavoritesBeer toEntity(FavoritesBeerDto favoritesBeerDto);

}
