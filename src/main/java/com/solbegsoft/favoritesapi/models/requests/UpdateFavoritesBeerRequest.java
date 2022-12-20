package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;
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
