package com.solbegsoft.favoritesapi.models.dtos;


import com.solbegsoft.favoritesapi.models.requests.AbstractFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Save Request Dto
 */
@Data
@SuperBuilder
public class SaveRequestDto extends BaseRequestDto{

    /**
     * @see AbstractFavoritesBeerRequest
     */
    private SaveFavoritesBeerRequest requestFavoritesBeer;

}
