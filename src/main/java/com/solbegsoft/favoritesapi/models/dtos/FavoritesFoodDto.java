package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * Favorites Food Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class FavoritesFoodDto extends BaseDto{

    /**
     * Food description
     */
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FavoritesFoodDto that = (FavoritesFoodDto) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
