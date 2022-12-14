package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;

import java.util.UUID;

/**
 * Service FavoritesBeer with FavoritesFood
 */
public interface BeerAndFoodService {

    /**
     * Get Favorites Beer with Food
     *
     * @param userId User Id
     * @param beerId ID of Favorites Beer
     * @return {@link ResponseBeerWithFood}
     */
    ResponseBeerWithFood getBeerWithFoodByBeerId(UUID userId, UUID beerId);
}