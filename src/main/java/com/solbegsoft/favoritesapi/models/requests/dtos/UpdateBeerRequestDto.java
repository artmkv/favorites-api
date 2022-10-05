package com.solbegsoft.favoritesapi.models.requests.dtos;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.UUID;

/**
 * Update Request Dto
 */
@Data
@SuperBuilder
public class UpdateBeerRequestDto extends BaseRequestDto {

    /**
     * ID
     */
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    private Long foreignBeerApiId;

    /**
     * Rating
     */
    private Integer rate;

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
        UpdateBeerRequestDto that = (UpdateBeerRequestDto) o;
        return Double.compare(that.abv, abv) == 0 && Double.compare(that.ibu, ibu) == 0 && Double.compare(that.ebc, ebc) == 0 && Objects.equals(id, that.id) && Objects.equals(foreignBeerApiId, that.foreignBeerApiId) && Objects.equals(rate, that.rate) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, foreignBeerApiId, rate, name, abv, ibu, ebc);
    }
}
