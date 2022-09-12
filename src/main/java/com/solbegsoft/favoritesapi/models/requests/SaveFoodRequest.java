package com.solbegsoft.favoritesapi.models.requests;


import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Save Food Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveFoodRequest extends AbstractRequest{

    /**
     * Description of food
     */
    @NotBlank
    private String text;

}
