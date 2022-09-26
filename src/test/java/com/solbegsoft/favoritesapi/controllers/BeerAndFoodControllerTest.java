package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BeerAndFoodControllerTest extends AbstractControllerTest {

    /**
     * @see FoodRepository
     */
    @MockBean
    private FoodRepository foodRepository;

    /**
     * @see BeerRepository
     */
    @MockBean
    private BeerRepository beerRepository;

    /**
     * Constructor
     */
    protected BeerAndFoodControllerTest() {
        super("d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4");
    }

    /**
     * Test method
     *
     * @throws Exception
     */
    @Test
    void testGetFavoritesBeerWithFoodByBeerId_ShouldReturnBeerAndListFood() throws Exception {
        UUID beerUUID = UUID.randomUUID();
        FavoritesBeer beer = create(10L, userIdUUID, 3, "Beer");
        Optional<FavoritesBeer> optionalBeer = Optional.of(beer);
        List<FavoritesFood> listFood = new ArrayList<>();
        listFood.add(new FavoritesFood(UUID.randomUUID(), userIdUUID, 10L, "Food", 5));

        when(beerRepository.findOneBeerById(userIdUUID, beerUUID)).thenReturn(optionalBeer);
        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(listFood);

        mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + beerUUID)
                                .header("userID", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.beer.foreignBeerApiId").value(10));
    }

    /**
     * Test method {@link BeerAndFoodController#getFavoritesBeerWithFoodByBeerId(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetFavoritesBeerWithFoodByBeerId_ShouldReturnBeerAndEmptyListFood() throws Exception {
        UUID beerUUID = UUID.randomUUID();
        FavoritesBeer beer = create(10L, userIdUUID, 3, "Beer");
        Optional<FavoritesBeer> optionalBeer = Optional.of(beer);
        List<FavoritesFood> listFood = new ArrayList<>();

        when(beerRepository.findOneBeerById(userIdUUID, beerUUID)).thenReturn(optionalBeer);
        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(listFood);

        mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + beerUUID)
                                .header("userID", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.beer.foreignBeerApiId").value(10))
                .andExpect(jsonPath("$.data.foods").value(Matchers.empty()));
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
    private FavoritesBeer create(Long beerId, UUID userId, Integer rate, String name) {

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