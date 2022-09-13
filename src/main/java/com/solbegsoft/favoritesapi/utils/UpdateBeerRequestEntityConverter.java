package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;

/**
 * Converter Request and {@link FavoritesBeerDto}
 */
public class UpdateBeerRequestEntityConverter {

    /**
     * Instance
     */
    public static final UpdateBeerRequestEntityConverter INSTANCE = new UpdateBeerRequestEntityConverter();

    /**
     * Convert to {@link FavoritesBeerDto}
     *
     * @param request {@link UpdateBeerRequestDto}
     * @return {@link FavoritesBeerDto}
     */
    public FavoritesBeerDto convertRequestToFavoritesBeer(UpdateBeerRequestDto request) {

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
}
