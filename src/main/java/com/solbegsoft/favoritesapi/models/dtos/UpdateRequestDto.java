package com.solbegsoft.favoritesapi.models.dtos;


import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Update Request Dto
 */
@Data
@SuperBuilder
public class UpdateRequestDto extends BaseRequestDto {

    // TODO: 05.09.2022 Зачем объект, просто достань поля и кинь их сюда!
    /**
     * @see UpdateFavoritesBeerRequest
     */
    private UpdateFavoritesBeerRequest requestFavoritesBeer;

}
