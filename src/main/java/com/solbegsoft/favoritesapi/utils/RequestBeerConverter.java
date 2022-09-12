package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

/**
 * Converter Request Beer
 */
public class RequestBeerConverter {

    /**
     * Instance
     */
    public static final RequestBeerConverter INSTANCE = new RequestBeerConverter();

    /**
     * Convert request parameters to {@link GetRequestDto}
     *
     * @param userId   User Id
     * @param rate     rate
     * @param pageable {@link Pageable}
     * @return {@link GetRequestDto}
     */
    public GetRequestDto convertToGetRequestDto(UUID userId, Set<Integer> rate, Pageable pageable) {

        return GetRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .pageable(pageable)
                .build();
    }

    /**
     * Convert request parameters to {@link SaveRequestDto}
     *
     * @param userId  User Id
     * @param request {@link SaveFavoritesBeerRequest}
     * @return {@link SaveRequestDto}
     */
    public SaveRequestDto convertToSaveRequestDto(UUID userId, SaveFavoritesBeerRequest request) {

        return SaveRequestDto.builder()
                .userId(userId)
                .foreignBeerApiId(request.getForeignBeerApiId())
                .rate(request.getRate())
                .name(request.getName())
                .ebc(request.getEbc())
                .ibu(request.getIbu())
                .abv(request.getAbv())
                .build();
    }

    /**
     * Convert request parameters to {@link UpdateRequestDto}
     *
     * @param userId  User Id
     * @param id      ID
     * @param request {@link UpdateFavoritesBeerRequest}
     * @return {@link UpdateRequestDto}
     */
    public UpdateRequestDto convertToUpdateRequestDto(UUID userId, UUID id, UpdateFavoritesBeerRequest request) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .id(id)
                .foreignBeerApiId(request.getForeignBeerApiId())
                .rate(request.getRate())
                .name(request.getName())
                .ebc(request.getEbc())
                .ibu(request.getIbu())
                .abv(request.getAbv())
                .build();
    }

    /**
     * Convert request parameters to {@link UpdateRequestDto}
     *
     * @param userId User Id
     * @param id     ID
     * @param rate   rate
     * @return {@link UpdateRequestDto}
     */
    public UpdateRequestDto convertToUpdateRequestDto(UUID userId, UUID id, Integer rate) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .id(id)
                .build();
    }
}
