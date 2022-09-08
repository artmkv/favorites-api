package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * Abstract Favorites Beer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractRequest {

    /**
     * Rate
     */
    @NotNull
    @DecimalMax(value = "5")
    private Integer rate;

    /**
     * Beer ID
     */
    @NotNull
    private Long foreignBeerApiId;
}
