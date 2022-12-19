package com.solbegsoft.favoritesapi.models.dtos;


import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Favorites Food Dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FavoritesFoodDto extends BaseDto{

    /**
     * Food description
     */
    private String text;

}
