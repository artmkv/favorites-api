package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import com.solbegsoft.favoritesapi.utils.RequestFoodConverter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link FoodController}
 */
class FoodControllerTest extends AbstractControllerTest {

    /**
     * @see FoodRepository
     */
    @MockBean
    private FoodRepository foodRepository;

    @Override
    protected String getEndPoint() {
        return "/favorites-api/v1/food" ;
    }

    /**
     * Test with correct method {@link FoodController#getFavoritesFoodByString(UUID, String)}
     * and return Empty list
     *
     * @param searchString search string
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"bdfbd", "___", "pizza1", "1pizza"})
    @EmptySource
    @NullSource
    void testGetFavoritesFoodByString_WhenAnyString_ShouldReturnEmptyList(String searchString) throws Exception {
        List<FavoritesFood> list = new ArrayList<>();
        when(foodRepository.findAllFavoritesFood(userIdUUID)).thenReturn(list);

        mockMvc.perform(
                        get(getEndPoint())
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(Matchers.empty()));
    }

    /**
     * Test with correct method {@link FoodController#getFavoritesFoodByString(UUID, String)}
     *
     * @param searchString search string
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"zz", "izz", "pi"})
    void testGetFavoritesFoodByString_WithCorrectString_ShouldListWithOneItem(String searchString) throws Exception {
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, 1L, "Pizza", 5));
        when(foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString)).thenReturn(list);
        mockMvc.perform(
                        get(getEndPoint())
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].text").value("Pizza"))
                .andExpect(jsonPath("$.data[1]").doesNotExist());
    }

    /**
     * Test with correct method {@link FoodController#getFavoritesFoodByString(UUID, String)}
     *
     * @param searchString search string
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"zz", "izz", "Pi"})
    void testGetFavoritesFoodByString_WithCorrectString_ShouldListWithTwoItem(String searchString) throws Exception {
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, 1L, "Pizza", 5));
        list.add(createFavoritesFood(userIdUUID, 2L, "Burger and pizza", 3));
        List<FavoritesFoodDto> expectedList = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(list);

        when(foodRepository.findAllFavoritesFood(userIdUUID)).thenReturn(list);
        String actualAsString = mockMvc.perform(
                        get(getEndPoint())
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TypeReference<ResponseApi<List<FavoritesFoodDto>>> typeReference = new TypeReference<>() {
        };
        ResponseApi<List<FavoritesFoodDto>> responseListBeers = objectMapper.readValue(actualAsString, typeReference);
        List<FavoritesFoodDto> actualList = responseListBeers.getData();
        assertEquals(expectedList, actualList);
    }

    /**
     * Test without search string method {@link FoodController#getFavoritesFoodByString(UUID, String)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetFavoritesFoodByString_WithoutSearchStringByUserID_ShouldListWithAllItem() throws Exception {
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, 1L, "Pizza", 5));
        list.add(createFavoritesFood(userIdUUID, 2L, "Burger and pizza", 3));
        list.add(createFavoritesFood(userIdUUID, 3L, "Spacy Tacos", 2));
        List<FavoritesFoodDto> expectedList = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(list);

        when(foodRepository.findAllFavoritesFood(userIdUUID)).thenReturn(list);

        String actualAsString = mockMvc.perform(
                        get(getEndPoint())
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TypeReference<ResponseApi<List<FavoritesFoodDto>>> typeReference = new TypeReference<>() {        };
        ResponseApi<List<FavoritesFoodDto>> responseListBeers = objectMapper.readValue(actualAsString, typeReference);
        List<FavoritesFoodDto> actualList = responseListBeers.getData();
        assertEquals(expectedList, actualList);
    }

    /**
     * Test method {@link FoodController#saveFavoritesFood(UUID, SaveFavoritesFoodRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveFavoritesFood_WithCorrectRequest_ShouldReturnStatusCreatedWithFavoritesFoodDto() throws Exception {

        SaveFavoritesFoodRequest request = createSaveRequest(15L, "A", 1);

        FavoritesFoodDto expected = RequestFoodConverter.getFoodDtoFromRequest(userIdUUID, request);
        FavoritesFood entity = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(expected);
        expected.setId(UUID.randomUUID());
        FavoritesFood returnBeer = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(expected);

        when(foodRepository.save(entity)).thenReturn(returnBeer);
        String actualAsString = mockMvc.perform(post(getEndPoint())
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        TypeReference<ResponseApi<FavoritesFoodDto>> typeReference = new TypeReference<>() {
        };
        FavoritesFoodDto actual = objectMapper.readValue(actualAsString, typeReference).getData();
        assertEquals(expected, actual);
    }

    /**
     * Test with empty Request method {@link FoodController#saveFavoritesFood(UUID, SaveFavoritesFoodRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveFavoritesFood_WithEmptyRequest_ShouldReturnStatusBadRequest() throws Exception {
        mockMvc.perform(post(getEndPoint())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Required request body is missing")));
    }

    /**
     * Test method {@link FoodController#deleteFavoritesFood(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testDeleteFavoritesFood_WhenEntity_ShouldReturnTrue() throws Exception {
        UUID foodId = UUID.randomUUID();
        mockMvc.perform(delete(getEndPointWithBeerId(foodId))
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.data").value(Boolean.TRUE));
    }

    /**
     * Create {@link  SaveFavoritesFoodRequest}
     *
     * @param foreignBeerId Foreign Beer ID
     * @param text          text
     * @param rate          rate
     * @return {@link  SaveFavoritesFoodRequest}
     */
    private SaveFavoritesFoodRequest createSaveRequest(Long foreignBeerId, String text, Integer rate) {
        SaveFavoritesFoodRequest request = new SaveFavoritesFoodRequest();
        request.setForeignBeerApiId(foreignBeerId);
        request.setText(text);
        request.setRate(rate);

        return request;
    }

    /**
     * Create {@link FavoritesFood}
     *
     * @param userId        UserId
     * @param foreignBeerId Foreign Beer ID
     * @param text          Text
     * @param rate          rate
     * @return {@link FavoritesFood}
     */
    private FavoritesFood createFavoritesFood(UUID userId, Long foreignBeerId, String text, Integer rate) {
        FavoritesFood food = new FavoritesFood();
        food.setId(UUID.randomUUID());
        food.setUserId(userId);
        food.setText(text);
        food.setForeignBeerApiId(foreignBeerId);
        food.setRate(rate);

        return food;
    }
}
