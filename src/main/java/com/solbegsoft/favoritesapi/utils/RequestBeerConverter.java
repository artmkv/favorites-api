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
 * Request Converter
 */
public class RequestBeerConverter {

    public static final RequestBeerConverter INSTANCE = new RequestBeerConverter();

    public GetRequestDto convertToGetRequestDto(UUID userId, Set<Integer> rate, Pageable pageable) {

        return GetRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .pageable(pageable)
                .build();
    }

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

    public UpdateRequestDto convertToUpdateRequestDto(UUID userId, UUID id, Integer rate) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .id(id)
                .build();
    }
}
