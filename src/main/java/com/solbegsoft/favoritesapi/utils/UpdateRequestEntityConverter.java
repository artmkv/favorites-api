package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateRequestDto;

/**
 * Converter Request and {@link FavoritesBeer}
 */
public class UpdateRequestEntityConverter {

    /**
     * Instance
     */
    public static final UpdateRequestEntityConverter INSTANCE = new UpdateRequestEntityConverter();

    /**
     * Convert to entity {@link FavoritesBeer}
     *
     * @param request {@link UpdateRequestDto}
     * @return {@link FavoritesBeer}
     */
    public FavoritesBeer convertRequestToFavoritesBeer(UpdateRequestDto request) {

        return FavoritesBeer.builder()
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
