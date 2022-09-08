package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * Favorites Beer Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class FavoritesBeerDto extends BaseDto{

    /**
     * name
     */
    private String name;

    /**
     * ABV
     */
    private double abv;

    /**
     * IBU
     */
    private double ibu;

    /**
     * EBC
     */
    private double ebc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FavoritesBeerDto dto = (FavoritesBeerDto) o;
        return Double.compare(dto.abv, abv) == 0 && Double.compare(dto.ibu, ibu) == 0 && Double.compare(dto.ebc, ebc) == 0 && Objects.equals(name, dto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, abv, ibu, ebc);
    }
}
