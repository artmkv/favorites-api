package com.solbegsoft.favoritesapi.models.requests;


import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * Request model for Favorites beer
 */
@Data
public class RequestFavoritesBeer {

    /**
     * ID
     */
    private Integer id;

    /**
     * Beer ID
     */
    @NotNull
    private Integer beerId;

    /**
     * Rate
     */
    @DecimalMax(value = "5")
    private Integer rate;
}
