package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Request model for Save Favorites beer
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
    @NotBlank
    private UUID id;

    private Long beerId;

}
