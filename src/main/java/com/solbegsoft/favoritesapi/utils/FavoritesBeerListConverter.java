package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * List Mapper
 */
@Mapper(componentModel = "spring", uses = FavoritesBeerConverter.class)
public interface FavoritesBeerListConverter {

    /**
     * Instance
     */
    FavoritesBeerListConverter INSTANCE = Mappers.getMapper(FavoritesBeerListConverter.class);
    List<FavoritesBeer> toEntityList(List<FavoritesBeerDto> dtos);

    List<FavoritesBeerDto> toDtoList(List<FavoritesBeer> entities);
}
