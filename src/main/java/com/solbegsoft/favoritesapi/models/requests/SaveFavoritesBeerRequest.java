package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

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
     * name
     */
    private String name;

    /**
     * ABV
     */
    private double abv;

    /**
     * IBU
     */
    private double ibu;

    /**
     * EBC
     */
    private double ebc;

}
