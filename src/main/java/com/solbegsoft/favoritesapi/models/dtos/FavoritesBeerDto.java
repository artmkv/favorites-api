package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Favorites Beer Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
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

}
