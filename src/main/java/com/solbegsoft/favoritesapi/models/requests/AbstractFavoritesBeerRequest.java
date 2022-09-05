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
public class AbstractFavoritesBeerRequest { // TODO: 05.09.2022 он абстрактный или нет?

    /**
     * Rate
     */
    @NotNull
    @DecimalMax(value = "5")
    private Integer rate;
}
