package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link FoodController}
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FoodControllerTest {

    /**
     * @see FoodRepository
     */
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @ParameterizedTest
    @ValueSource(strings = {"d4ce25e2-22ac-11ed", "___", "  ", "543422", "fdja!y&"})
    void testForAnyUserId(String userIdString) throws Exception {

        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
        mockMvc.perform(
                        post("/favorites-api/v1/food")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
        mockMvc.perform(
                        delete("/favorites-api/v1/food")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    void testIfNotExistUserId() throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                        post("/favorites-api/v1/food")
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                        delete("/favorites-api/v1/food")
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @EmptySource
    void testIfEmptyUserId(String stringUserId) throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                        post("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                        delete("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bdfbd", "  ", "", "!!!"})
    @EmptySource
    @NullSource
    void testFindByAnyString(String searchString) throws Exception {
        String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";


        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", searchString)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        UUID userId = UUID.fromString(stringUserId);
        foodRepository.save(new FavoritesFood(UUID.randomUUID(), userId, 10L, "A", 5));
        String search = "A";
        mockMvc.perform(
                        get("/favorites-api/v1/food")
                                .header("userId", stringUserId)
                                .param("search", search)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.name").value("A"));

    }
}
