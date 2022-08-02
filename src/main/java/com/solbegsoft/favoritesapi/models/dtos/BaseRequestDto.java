package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Base request
 *
 * @param <T> Serializable type of USER ID
 */
@Data
@SuperBuilder
public abstract class BaseRequestDto<T extends Serializable> {

    /**
     * User ID
     */
    @NotNull
    private T userId;

}
