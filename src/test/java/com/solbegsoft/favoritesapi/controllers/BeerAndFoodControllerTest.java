package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * Test method {@link BeerAndFoodController#getFavoritesBeerWithFoodByBeerId(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetFavoritesBeerWithFoodByBeerId_ShouldReturnBeerAndListFood() throws Exception {
        FavoritesBeer beer = create(10L, userIdUUID, 3, "Beer");
        Optional<FavoritesBeer> optionalBeer = Optional.of(beer);
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        List<FavoritesFood> foods = new ArrayList<>();
        foods.add(new FavoritesFood(UUID.randomUUID(), userIdUUID, 10L, "Food", 5));
        List<FavoritesFoodDto> expectedFoods = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(foods);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optionalBeer);
        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(foods);

        String actualString = mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + beer.getId())
                                .header("userID", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        TypeReference<ResponseApi<ResponseBeerWithFood>> typeReference = new TypeReference<>() {
        };

        ResponseApi<ResponseBeerWithFood> responseBeerWithFood = objectMapper.readValue(actualString, typeReference);
        FavoritesBeerDto actualBeer = responseBeerWithFood.getData().getBeer();
        List<FavoritesFoodDto> actualFoods = responseBeerWithFood.getData().getFoods();
        checkAssertionsEqualsFieldsOfFavoritesBeer(expectedBeer, actualBeer);
        checkAssertionsEqualsFieldsInListOfFavoritesFood(expectedFoods, actualFoods);
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
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        Optional<FavoritesBeer> optionalBeer = Optional.of(beer);
        List<FavoritesFood> listFood = new ArrayList<>();

        when(beerRepository.findOneBeerById(userIdUUID, beerUUID)).thenReturn(optionalBeer);
        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, beer.getForeignBeerApiId())).thenReturn(listFood);

        String actualString = mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + beerUUID)
                                .header("userID", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.foods").value(Matchers.empty()))
                .andReturn().getResponse().getContentAsString();
        TypeReference<ResponseApi<ResponseBeerWithFood>> typeReference = new TypeReference<>() {
        };

        ResponseApi<ResponseBeerWithFood> responseBeerWithFood = objectMapper.readValue(actualString, typeReference);
        FavoritesBeerDto actualBeer = responseBeerWithFood.getData().getBeer();
        checkAssertionsEqualsFieldsOfFavoritesBeer(expectedBeer, actualBeer);
    }

    /**
     * Test should Return EmptyBody method {@link BeerAndFoodController#getFavoritesBeerWithFoodByBeerId(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetFavoritesBeerWithFoodByBeerId_WithBeerId_ShouldReturnEmptyBody() throws Exception {
        UUID beerUUID = UUID.randomUUID();
        Optional<FavoritesBeer> optionalBeer = Optional.empty();
        List<FavoritesFood> listFood = new ArrayList<>();
        when(beerRepository.findOneBeerById(userIdUUID, beerUUID)).thenReturn(optionalBeer);

        mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + beerUUID)
                                .header("userID", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data").value(Matchers.nullValue()));
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

    /**
     * Check all field of Favorites Beer
     *
     * @param expected expected {@link FavoritesBeerDto}
     * @param actual   actual {@link FavoritesBeerDto}
     */
    void checkAssertionsEqualsFieldsOfFavoritesBeer(FavoritesBeerDto expected, FavoritesBeerDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAbv(), actual.getAbv());
        assertEquals(expected.getEbc(), actual.getEbc());
        assertEquals(expected.getIbu(), actual.getIbu());
    }

    /**
     * Check all  FavoritesFood in List
     *
     * @param expected expected List {@link FavoritesFoodDto}
     * @param actual   actual List of {@link FavoritesFoodDto}
     */
    void checkAssertionsEqualsFieldsInListOfFavoritesFood(List<FavoritesFoodDto> expected, List<FavoritesFoodDto> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            checkAssertionsEqualsFieldsOfFavoritesFood(expected.get(i), actual.get(i));
        }
    }

    /**
     * Check all field of Favorites Food
     *
     * @param expected expected {@link FavoritesFoodDto}
     * @param actual   actual {@link FavoritesFoodDto}
     */
    void checkAssertionsEqualsFieldsOfFavoritesFood(FavoritesFoodDto expected, FavoritesFoodDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
    }
}