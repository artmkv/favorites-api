package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;

import java.util.UUID;

/**
 * Favorites Beer Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritesBeerDto {

    /**
     * ID
     */
    private UUID uuid;

    /**
     * Id_Beer from Beers_API
     */
    private Long beerId;

    /**
     * User Id
     */
    private Long userId;

    /**
     * Rating
     */
    private Integer rate;
}
