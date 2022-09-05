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
public class FavoritesBeerDto { // extends BaseRequestDto

    /**
     * ID
     */
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    private Long beerId;

    // TODO: 05.09.2022 у тебя же есть  BaseRequestDto
    /**
     * User Id
     */
    private UUID userId;

    /**
     * Rating
     */
    private Integer rate;
}
