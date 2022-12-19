package com.solbegsoft.favoritesapi.models.requests.dtos;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * Base request Dto
 */
@Data
@SuperBuilder
public class BaseRequestDto {

    /**
     * User ID
     */
    private UUID userId;

}
