package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * Request model for Save Favorites beer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class SaveFavoritesBeerRequest extends AbstractFavoritesBeerRequest{

    /**
     * Beer ID
     */
    @NotNull
    private Long beerId;

}
