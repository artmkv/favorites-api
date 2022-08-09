package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public class UpdateFavoritesBeerRequest extends AbstractFavoritesBeerRequest{

    /**
     * ID
     */
    @NotNull
    private UUID id;

    /**
     * beer ID
     */
    @NotNull
    private Long beerId;

}
