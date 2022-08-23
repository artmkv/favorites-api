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
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    private Long beerId;

    /**
     * User Id
     */
    private UUID userId;

    /**
     * Rating
     */
    private Integer rate;
}
