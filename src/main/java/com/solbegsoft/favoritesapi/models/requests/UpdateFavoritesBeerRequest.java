package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Request model for Update Favorites beer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateFavoritesBeerRequest extends AbstractRequest {

    /**
     * ID
     */
    @NotNull
    private UUID id;

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
