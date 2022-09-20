package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Save Food Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveFavoritesFoodRequest extends AbstractRequest{

    /**
     * Description of food
     */
    @NotBlank
    @NotNull
    private String text;

}
