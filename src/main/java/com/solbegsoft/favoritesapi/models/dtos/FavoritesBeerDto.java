package com.solbegsoft.favoritesapi.models.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Favorites Beer Dto
 */
@Data
@Builder
@RequiredArgsConstructor
public class FavoritesBeerDto {

    /**
     * ID
     */
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    private Integer beerId;

    /**
     * User Id
     */
    private Integer userId;

    /**
     * Rating
     */
    private Integer rate;
}
