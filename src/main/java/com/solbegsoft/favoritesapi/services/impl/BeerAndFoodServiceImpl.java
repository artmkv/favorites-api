package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;
import com.solbegsoft.favoritesapi.services.BeerAndFoodService;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.services.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Food And Beer Service
 */
@Repository
@RequiredArgsConstructor
public class BeerAndFoodServiceImpl implements BeerAndFoodService {

    /**
     * @see BeerService
     */
    private final BeerService beerService;

    /**
     * @see FoodService
     */
    private final FoodService foodService;

    @Override
    public ResponseBeerWithFood getBeerWithFoodByBeerId(UUID userId, UUID id) {

        FavoritesBeerDto beer = beerService.getBeerById(userId, id);
        List<FavoritesFoodDto> food = foodService.getListOfFoodByBeerId(userId, beer.getForeignBeerApiId());

        return new ResponseBeerWithFood(beer, food);
    }
}
