package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public class AbstractFavoritesBeerRequest {

    /**
     * Rate
     */
    @NotNull
    @DecimalMax(value = "5")
    private Integer rate;
}
