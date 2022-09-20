package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link FoodController}
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FoodControllerTest {

    /**
     * @see FoodRepository
     */
    @MockBean
    private FoodRepository mockRepository;

    /**
     * @see MockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * @see ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * UserId before Authentication
     */
    private String stringUserId;

    @BeforeEach
    void initUser() {
        this.stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";
    }

    @ParameterizedTest
    @ValueSource(strings = {"bdfbd", "___", "pizza1", "1pizza"})
    void testGetFavoritesFoodByString_WhenAnyString_Should(String searchString) throws Exception {
        UUID userId = UUID.fromString(stringUserId);

        List<FavoritesFood> list = new ArrayList<>();
        list.add(create(userId, 1L, "Pizza", 5));
        list.add(create(userId, 2L, "Soup", 4));
        list.add(create(userId, 3L, "Burger", 3));
        when(mockRepository.findAllFavoritesFood(userId)).thenReturn(list);

        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data").value(Matchers.empty()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"zz", "izz", "pi"})
    void testGetFavoritesFoodByString_WithCorrectString_ShouldList(String searchString) throws Exception {
        UUID userId = UUID.fromString(stringUserId);

        List<FavoritesFood> list = new ArrayList<>();
        list.add(create(userId, 1L, "Pizza", 5));
        List<FavoritesFood> listAll = new ArrayList<>();
        listAll.addAll(list);
        listAll.add(create(userId, 2L, "Soup", 4));
        listAll.add(create(userId, 3L, "Burger", 3));
        when(mockRepository.findAllFavoritesFood(userId)).thenReturn(listAll);
        when(mockRepository.findAllFavoritesFoodByString(userId, searchString)).thenReturn(list);

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

    @Test
    void testFindByCorrectString() throws Exception {
        String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";
        UUID userId = UUID.fromString(stringUserId);

        mockRepository.save(create(userId, 10L, "CH", 5));
        mockRepository.save(create(userId, 8L, "Barbeque", 3));

        List<FavoritesFood> all = mockRepository.findAll();
        List<FavoritesFood> allFavoritesFood = mockRepository.findAllFavoritesFood(userId);

        String search = "C";
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
//                                .param("search", search)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data[0].text").value("CH"))
//                .andExpect(jsonPath("$.rate").value(5))
        ;
    }

    @Test
    void testSaveFavoritesFood() throws Exception {
        String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";
        UUID userId = UUID.fromString(stringUserId);
        SaveFavoritesFoodRequest request = createSaveRequest(15L, "A", 1);

        mockMvc.perform(post("/favorites-api/v1/food")
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(stringUserId))
                ).andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(post("/favorites-api/v1/food")
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(post("/favorites-api/v1/food")
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.text").value(request.getText()))
        ;
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
    private FavoritesFood create(UUID userId, Long foreignBeerId, String text, Integer rate) {
        FavoritesFood food = new FavoritesFood();
        food.setId(UUID.randomUUID());
        food.setUserId(userId);
        food.setText(text);
        food.setForeignBeerApiId(foreignBeerId);
        food.setRate(rate);

        return food;
    }
}
