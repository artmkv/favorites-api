package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Converter for {@link FavoritesBeer} and {@link FavoritesBeerDto}
 */
@Mapper(componentModel = "spring")
public interface FavoritesBeerConverter {

    /**
     * Instance
     */
    FavoritesBeerConverter INSTANCE = Mappers.getMapper(FavoritesBeerConverter.class);

    /**
     * Convert {@link FavoritesBeer} to {@link FavoritesBeerDto}
     *
     * @param favoritesBeer {@link FavoritesBeer}
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto toDtoFromFavoritesBeer(FavoritesBeer favoritesBeer);

    /**
     * Convert {@link FavoritesBeerDto} to {@link FavoritesBeer}
     *
     * @param favoritesBeerDto {@link FavoritesBeerDto}
     * @return {@link FavoritesBeer}
     */
    FavoritesBeer toFavoritesBeerFromDto(FavoritesBeerDto favoritesBeerDto);

}
