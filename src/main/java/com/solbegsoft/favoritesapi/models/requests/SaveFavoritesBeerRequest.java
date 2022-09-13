package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Request model for Save Favorites beer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveFavoritesBeerRequest extends AbstractRequest {

    /**
     * Name
     */
    @NotBlank
    private String name;

    /**
     * ABV
     */
    @NotNull
    private double abv;

    /**
     * IBU
     */
    @NotNull
    private double ibu;

    /**
     * EBC
     */
    @NotNull
    private double ebc;

}
