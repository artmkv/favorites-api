package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.exceptions.BadRequestOrPathException;
import com.solbegsoft.favoritesapi.models.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * Request Converter
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class RequestFavoritesBeerToRequestDtoConverter {

    private static final RequestFavoritesBeerToRequestDtoConverter INSTANCE = new RequestFavoritesBeerToRequestDtoConverter();

    /**
     * @return instance of {@link RequestFavoritesBeerToRequestDtoConverter}
     */
    public RequestFavoritesBeerToRequestDtoConverter getInstance() {
        return INSTANCE;
    }

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
                .requestFavoritesBeer(request)
                .build();
    }

    public UpdateRequestDto convertToUpdateRequestDto(UUID userId, UUID id, UpdateFavoritesBeerRequest request) {
        if (!id.equals(request.getId())) {
            throw new BadRequestOrPathException(
                    String.format(ExceptionMessagesConstant.MISMATCH_ID_IN_REQUEST, id, request.getId())
            );
        }
        return UpdateRequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(request)
                .build();
    }

    public UpdateRequestDto convertToUpdateRequestDto(UUID userId, UUID id, Integer rate) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(UpdateFavoritesBeerRequest.builder()
                        .id(id)
                        .rate(rate)
                        .build())
                .build();
    }
}
