package com.solbegsoft.favoritesapi.controllers.validation;


import com.solbegsoft.favoritesapi.controllers.AbstractMVCTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Validate header UserId in BeerAndFoodControllerTest
 */
class BeerAndFoodControllerValidationTest extends AbstractMVCTest {

    /**
     * String UUID beer ID
     */
    private final String stringBeerId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";

    /**
     * Validate header UserID for any values
     *
     * @param userIdString string of parameter
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"d4ce25e2-22ac-11ed", "___", "3453455!?34r", "ff _-$ 3)"})
    void testAllEP_WhenNotValidUserId_ShouldStatus4xx(String userIdString) throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + stringBeerId)
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));
    }

    /**
     * Validate EndPoint if header UserID is not exist
     *
     * @throws Exception exception
     */
    @Test
    void testAllEP_WhenNotExistUserId_ShouldStatus4xx() throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/beer-with-food/" + stringBeerId)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("Body").value(Matchers.nullValue()));
    }
}