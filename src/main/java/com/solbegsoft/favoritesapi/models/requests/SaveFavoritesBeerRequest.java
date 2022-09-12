package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private double abv;

    /**
     * IBU
     */
    @NotBlank
    private double ibu;

    /**
     * EBC
     */
    @NotBlank
    private double ebc;

}
