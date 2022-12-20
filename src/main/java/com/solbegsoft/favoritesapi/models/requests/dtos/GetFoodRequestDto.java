package com.solbegsoft.favoritesapi.models.requests.dtos;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * Request GetFoodDto model
 */
@Data
@SuperBuilder
public class GetFoodRequestDto extends BaseRequestDto {

    /**
     * String to find food
     */
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GetFoodRequestDto that = (GetFoodRequestDto) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
