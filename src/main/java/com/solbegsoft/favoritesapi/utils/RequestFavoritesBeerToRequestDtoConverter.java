package com.solbegsoft.favoritesapi.utils;

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

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public final class RequestFavoritesBeerToRequestDtoConverter {

    private static RequestFavoritesBeerToRequestDtoConverter INSTANCE;

    public RequestFavoritesBeerToRequestDtoConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestFavoritesBeerToRequestDtoConverter();
        }
        return INSTANCE;
    }

//    private RequestFavoritesBeerToRequestDtoConverter() {
//    }

    public SaveRequestDto convertToSaveRequestDto(Long userId, SaveFavoritesBeerRequest request) {

        return SaveRequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(request)
                .build();
    }

    public UpdateRequestDto convertToUpdateRequestDto(Long userId, UUID id, UpdateFavoritesBeerRequest request) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(request)
                .build();
    }

    public UpdateRequestDto convertToUpdateRequestDto(Long userId, UUID id, Integer rate) {

        return UpdateRequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(UpdateFavoritesBeerRequest.builder()
                        .id(id)
                        .rate(rate)
                        .build())
                .build();
    }

    public GetRequestDto convertToGetRequestDto(Long userId, Set<Integer> rate, Pageable pageable) {

        return GetRequestDto.builder()
                .userId(userId)
                .rate(rate)
                .pageable(pageable)
                .build();
    }
}
