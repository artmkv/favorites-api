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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

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
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FoodControllerTest extends AbstractControllerTest {

    /**
     * @see FoodRepository
     */
    @MockBean
    private FoodRepository foodRepository;

    /**
     * Constructor
     */
    public FoodControllerTest() {
        super("d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4");
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
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
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
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
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
        when(foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString)).thenReturn(list);
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].text").value("Pizza"))
                .andExpect(jsonPath("$.data[1].userId").value(stringUserId))
                .andExpect(jsonPath("$.data[2]").doesNotExist());
    }

    /**
     * Test without search string method {@link FoodController#getFavoritesFoodByString(UUID, String)}
     *
     * @param searchString search string
     * @throws Exception exception
     */
    @ParameterizedTest
    @EmptySource
    @NullSource
    void testGetFavoritesFoodByString_WithoutSearchStringByUserID_ShouldListWithAllItem(String searchString) throws Exception {
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, 1L, "Pizza", 5));
        list.add(createFavoritesFood(userIdUUID, 2L, "Burger and pizza", 3));
        list.add(createFavoritesFood(userIdUUID, 3L, "Spacy Tacos", 2));
        when(foodRepository.findAllFavoritesFood(userIdUUID)).thenReturn(list);
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].userId").value(stringUserId))
                .andExpect(jsonPath("$.data[1].userId").value(stringUserId))
                .andExpect(jsonPath("$.data[2].userId").value(stringUserId))
                .andExpect(jsonPath("$.data[0].foreignBeerApiId").value(list.get(0).getForeignBeerApiId()))
                .andExpect(jsonPath("$.data[1].foreignBeerApiId").value(list.get(1).getForeignBeerApiId()))
                .andExpect(jsonPath("$.data[2].foreignBeerApiId").value(list.get(2).getForeignBeerApiId()))
                .andExpect(jsonPath("$.data[3]").doesNotExist());
    }

    /**
     * Test method {@link FoodController#saveFavoritesFood(UUID, SaveFavoritesFoodRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveFavoritesFood_WithCorrectRequest_ShouldReturnStatus2xxWithFavoritesFoodDto() throws Exception {

        SaveFavoritesFoodRequest request = createSaveRequest(15L, "A", 1);

        FavoritesFoodDto dto = RequestFoodConverter.getFoodDtoFromRequest(userIdUUID, request);
        FavoritesFood entity = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(dto);
        dto.setId(UUID.randomUUID());
        FavoritesFood expected = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(dto);

        when(foodRepository.save(entity)).thenReturn(expected);
        String actualAsString = mockMvc.perform(post("/favorites-api/v1/food")
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        TypeReference<ResponseApi<FavoritesFoodDto>> typeReference = new TypeReference<>() {
        };
        FavoritesFoodDto actual = objectMapper.readValue(actualAsString, typeReference).getData();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
    }

    /**
     * Test with empty Request method {@link FoodController#saveFavoritesFood(UUID, SaveFavoritesFoodRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveFavoritesFood_WithEmptyRequest_ShouldReturnStatus4xx() throws Exception {
        mockMvc.perform(post("/favorites-api/v1/food")
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Test method {@link FoodController#deleteFavoritesFood(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testDeleteFavoritesFood_WhenEntity_ShouldReturnTrue() throws Exception {
        UUID foodId = UUID.randomUUID();
        mockMvc.perform(delete("/favorites-api/v1/food/" + foodId)
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().is2xxSuccessful())
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
