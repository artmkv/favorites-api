package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.response.ResponseBeerAndFood;

import java.util.UUID;

/**
 * Service FavoritesBeer with FavoritesFood
 */
public interface BeerAndFoodService {

    /**
     * Get Favorites Beer with Food
     *
     * @param userId User Id
     * @param id     ID
     * @return {@link ResponseBeerAndFood}
     */
    ResponseBeerAndFood getBeerByIdWIthFood(UUID userId, UUID id);
}