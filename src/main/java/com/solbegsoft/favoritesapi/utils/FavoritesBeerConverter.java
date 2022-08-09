package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Converter
 */
@Mapper(componentModel = "spring")
public interface FavoritesBeerConverter {

    /**
     * Instance
     */
    FavoritesBeerConverter INSTANCE = Mappers.getMapper(FavoritesBeerConverter.class);

    FavoritesBeerDto toDto(FavoritesBeer favoritesBeer);

    FavoritesBeer toFavoritesBeerFromDto(FavoritesBeerDto favoritesBeerDto);

    @Mappings({
        @Mapping(source = "dto.requestFavoritesBeer.beerId", target = "beerId"),
        @Mapping(source = "dto.requestFavoritesBeer.rate", target = "rate"),
        @Mapping(source = "dto.userId", target = "userId")
    })
    FavoritesBeer toFavoritesBeerFromSaveRequestDto(SaveRequestDto dto);

    @Mappings({
            @Mapping(source = "dto.requestFavoritesBeer.beerId", target = "beerId"),
            @Mapping(source = "dto.requestFavoritesBeer.rate", target = "rate"),
            @Mapping(source = "dto.requestFavoritesBeer.id", target = "id"),
            @Mapping(source = "dto.userId", target = "userId")
    })
    FavoritesBeer toFavoritesBeerFromUpdateRequestDto(UpdateRequestDto dto);

    List<FavoritesBeer> toFavoritesBeersListFromListDtos(List<FavoritesBeerDto> listDtos);

    List<FavoritesBeerDto> toFavoritesBeersDtoListFromFavoritesBeer(List<FavoritesBeer> listFavoritesBeer);

    void updateFavoritesBeerFromDto(FavoritesBeerDto dto, @MappingTarget FavoritesBeer favoritesBeer);

}
