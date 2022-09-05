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
public class RequestFavoritesBeerToRequestDtoConverter { // TODO: 05.09.2022 поменять нейминг RequestConverter или нечто подобное

    // TODO: 05.09.2022 зачем тут INSTANCE
    private static final RequestFavoritesBeerToRequestDtoConverter INSTANCE = new RequestFavoritesBeerToRequestDtoConverter();

    /**
     * @return instance of {@link RequestFavoritesBeerToRequestDtoConverter}
     */
    // TODO: 05.09.2022 зачем нам тут геттер? У нас файнал поле в статике, можешь его сделать public
    //
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
        if (!id.equals(request.getId())) { // TODO: 05.09.2022 зачем нам тут эта проверка? ЗАчем мы передаем одну и ту же инфу о айди в пас и теле?
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
