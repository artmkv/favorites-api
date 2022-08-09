package com.solbegsoft.favoritesapi.models.dtos;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Base request
 */
@Data
@SuperBuilder
public abstract class BaseRequestDto {

    /**
     * User ID
     */
    @NotNull
    private Long userId;

}
