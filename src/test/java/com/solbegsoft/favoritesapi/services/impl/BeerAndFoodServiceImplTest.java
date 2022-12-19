package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;
import com.solbegsoft.favoritesapi.services.BeerAndFoodService;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.services.FoodService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * BeerAndFoodServiceImplTest
 */
class BeerAndFoodServiceImplTest extends AbstractServiceTest {

    /**
     * @see FoodService
     */
    @MockBean
    private FoodService foodService;

    /**
     * @see BeerService
     */
    @MockBean
    private BeerService beerService;

    /**
     * @see BeerAndFoodService
     */
    @Autowired
    private BeerAndFoodService beerAndFoodService;

    /**
     * Test method {@link BeerAndFoodServiceImpl#getBeerWithFoodByBeerId(UUID, UUID)}
     * return beer and list foods
     */
    @Test
    void getBeerWithFoodByBeerId_ShouldReturnResponseBeerAndListFood() {

        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 3, "Beer");
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        List<FavoritesFood> foods = createListFavoritesFood(userIdUUID);
        List<FavoritesFoodDto> expectedFoods = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(foods);
        ResponseBeerWithFood expected = new ResponseBeerWithFood(expectedBeer, expectedFoods);

        when(beerService.getBeerById(userIdUUID, beer.getId())).thenReturn(expectedBeer);
        when(foodService.getListOfFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(expectedFoods);

        ResponseBeerWithFood actual = beerAndFoodService.getBeerWithFoodByBeerId(userIdUUID, beer.getId());
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerAndFoodServiceImpl#getBeerWithFoodByBeerId(UUID, UUID)}
     *return empty list of food
     */
    @Test
    void getBeerWithFoodByBeerId_ShouldReturnResponseBeerAndEmptyListOfFood() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 3, "Beer");
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        List<FavoritesFoodDto> expectedFoods = Collections.emptyList();
        ResponseBeerWithFood expected = new ResponseBeerWithFood(expectedBeer, expectedFoods);

        when(beerService.getBeerById(userIdUUID, beer.getId())).thenReturn(expectedBeer);
        when(foodService.getListOfFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(expectedFoods);

        ResponseBeerWithFood actual = beerAndFoodService.getBeerWithFoodByBeerId(userIdUUID, beer.getId());
        assertEquals(expected.getBeer(), actual.getBeer());
        assertTrue(actual.getFoods().isEmpty());
    }

    /**
     * Create List Favorites Food
     *
     * @param userUUID userID
     * @return List of FavoritesFood
     */
    private List<FavoritesFood> createListFavoritesFood(UUID userUUID) {
        List<FavoritesFood> foods = new ArrayList<>();
        foods.add(new FavoritesFood(UUID.randomUUID(), userUUID, 10L, "Food", 5));
        foods.add(new FavoritesFood(UUID.randomUUID(), userUUID, 5L, "Soup", 3));
        return foods;
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @param beerId beerId
     * @param userId userId
     * @param rate   rate
     * @param name   name
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createFavoritesBeer(Long beerId, UUID userId, Integer rate, String name) {

        FavoritesBeer beer = new FavoritesBeer();
        beer.setId(UUID.randomUUID());
        beer.setForeignBeerApiId(beerId);
        beer.setUserId(userId);
        beer.setRate(rate);
        beer.setName(name);
        beer.setAbv(1.0);
        beer.setEbc(2.0);
        beer.setIbu(3.0);
        return beer;
    }
}