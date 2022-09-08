package com.solbegsoft.favoritesapi.models.requests;


import lombok.Data;

/**
 * Save Food Request
 */
@Data
public class SaveFoodRequest {

    private String text;

    private Integer rate;
}
