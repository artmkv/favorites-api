package com.solbegsoft.favoritesapi.models.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * Base request
 */
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDto {

    /**
     * User ID
     */
    private UUID userId;

    /**
     * ID
     */
    private UUID id;

    /**
     * Rating
     */
    private Integer rate;

    /**
     * Id_Beer from Beers_API
     */
    private Long foreignBeerApiId;

}
