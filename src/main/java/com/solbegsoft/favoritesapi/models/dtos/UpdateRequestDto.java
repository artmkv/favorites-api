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

    /**
     * @see UpdateFavoritesBeerRequest
     */
    private UpdateFavoritesBeerRequest requestFavoritesBeer;

}
