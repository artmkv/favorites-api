package com.solbegsoft.favoritesapi.models.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
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
    @NotNull
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
