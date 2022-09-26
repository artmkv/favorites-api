package com.solbegsoft.favoritesapi.controllers.validation;


import com.solbegsoft.favoritesapi.controllers.FoodController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Validate header UserId in {@link FoodController}
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FoodControllerValidationTest extends AbstractControllerValidationTest {

    /**
     * Validate header UserID in all EP for any values
     *
     * @param userIdString string of parameter
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"d4ce25e2-22ac-11ed", "___", "543422", "fdja!y&"})
    @EmptySource
    void testAllEP_WhenNotValidUserId_ShouldStatus4xx(String userIdString) throws Exception {

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

    /**
     * Validate EndPoint if header UserID is not exist
     *
     * @throws Exception exception
     */
    @Test
    void testAllEP_WhenNotExistUserId_ShouldStatus4xx() throws Exception {
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
}
