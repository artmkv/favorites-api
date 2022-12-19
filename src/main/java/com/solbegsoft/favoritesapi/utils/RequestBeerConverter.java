package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Converter Request Beer
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestBeerConverter {

    /**
     * Convert request parameters to {@link GetBeerRequestDto}
     *
     * @param userId   User Id
     * @param rate     rate
     * @param pageable {@link Pageable}
     * @return {@link GetBeerRequestDto}
     */
    public static GetBeerRequestDto convertToGetRequestDto(UUID userId, Integer[] rate, Pageable pageable) {
        Integer[] rateArray = Objects.requireNonNullElseGet(rate, () -> new Integer[]{});

        return GetBeerRequestDto.builder()
                .userId(userId)
                .rate(Set.of(rateArray))
                .pageable(pageable)
                .build();
    }

    /**
     * Convert request parameters to {@link SaveBeerRequestDto}
     *
     * @param userId  User Id
     * @param request {@link SaveFavoritesBeerRequest}
     * @return {@link SaveBeerRequestDto}
     */
    public static SaveBeerRequestDto convertToSaveRequestDto(UUID userId, SaveFavoritesBeerRequest request) {

        return SaveBeerRequestDto.builder()
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
     * Convert request parameters to {@link UpdateBeerRequestDto}
     *
     * @param userId  User Id
     * @param id      ID
     * @param request {@link UpdateFavoritesBeerRequest}
     * @return {@link UpdateBeerRequestDto}
     */
    public static UpdateBeerRequestDto convertToUpdateRequestDto(UUID userId, UUID id, UpdateFavoritesBeerRequest request) {

        return UpdateBeerRequestDto.builder()
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
     * Convert request parameters to {@link UpdateBeerRequestDto}
     *
     * @param userId User Id
     * @param id     ID
     * @param rate   rate
     * @return {@link UpdateBeerRequestDto}
     */
    public static UpdateBeerRequestDto convertToUpdateRequestDto(UUID userId, UUID id, Integer rate) {

        return UpdateBeerRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .id(id)
                .build();
    }
}
