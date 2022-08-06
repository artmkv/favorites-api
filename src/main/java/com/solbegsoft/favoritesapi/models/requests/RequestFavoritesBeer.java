package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.DecimalMax;
import java.util.UUID;

/**
 * Request model for Favorites beer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestFavoritesBeer {

    /**
     * UUID
     */
    private UUID uuid;

    /**
     * Beer ID
     */
    private Long beerId;

    /**
     * Rate
     */
    @DecimalMax(value = "5")
    private Integer rate;
}
