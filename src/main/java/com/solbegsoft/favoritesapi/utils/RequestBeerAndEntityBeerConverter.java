package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Converter Request and {@link FavoritesBeerDto}
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestBeerAndEntityBeerConverter {

    /**
     * Convert {@link UpdateBeerRequestDto} to {@link FavoritesBeerDto}
     *
     * @param request {@link UpdateBeerRequestDto}
     * @return {@link FavoritesBeerDto}
     */
    public static FavoritesBeerDto convertUpdateRequestToFavoritesBeer(UpdateBeerRequestDto request) {

        return FavoritesBeerDto.builder()
                .id(request.getId())
                .foreignBeerApiId(request.getForeignBeerApiId())
                .userId(request.getUserId())
                .rate(request.getRate())
                .name(request.getName())
                .abv(request.getAbv())
                .ebc(request.getEbc())
                .ibu(request.getIbu())
                .build();
    }

    /**
     * Convert {@link SaveBeerRequestDto} to {@link FavoritesBeerDto}
     *
     * @param request {@link SaveBeerRequestDto}
     * @return {@link FavoritesBeerDto}
     */
    public static FavoritesBeer convertSaveRequestToFavoritesBeer(SaveBeerRequestDto request) {

        FavoritesBeerDto dto = FavoritesBeerDto.builder()
                .foreignBeerApiId(request.getForeignBeerApiId())
                .userId(request.getUserId())
                .rate(request.getRate())
                .name(request.getName())
                .abv(request.getAbv())
                .ebc(request.getEbc())
                .ibu(request.getIbu())
                .build();

        return FavoritesBeerConverter.INSTANCE.toFavoritesBeerFromDto(dto);
    }
}
