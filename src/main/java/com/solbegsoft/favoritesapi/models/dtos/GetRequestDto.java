package com.solbegsoft.favoritesapi.models.dtos;


import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

/**
 * Request Beer Dto model
 */
@Data
@SuperBuilder
public class GetRequestDto extends BaseRequestDto{

    /**
     * FavoritesBeer ID
     */
    private UUID id;

    /**
     * Rate array
     */
    private Set<Integer> rate;

    /**
     * @see Pageable
     */
    private Pageable pageable;

}
