package com.solbegsoft.favoritesapi.controllers.validation;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Validate header UserId in {@link com.solbegsoft.favoritesapi.controllers.BeerController}
 */
class BeerControllerValidationTest extends AbstractValidationTest {

    /**
     * Validate header UserID for any values
     *
     * @param userIdString string of parameter
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"d4ce25e2-22ac-11ed", "___", "3453455!?34r", "# _- $)"})
    void testAllEP_WhenNotValidUserId_ShouldReturnStatusBadRequest(String userIdString) throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/beers")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));


        mockMvc.perform(
                        get("/favorites-api/v1/beers/" + stringBeerId)
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));

        mockMvc.perform(
                        post("/favorites-api/v1/beers")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Required request body is missing")));

        mockMvc.perform(
                        patch("/favorites-api/v1/beers/" + stringBeerId)
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));

        mockMvc.perform(
                        patch("/favorites-api/v1/beers/" + stringBeerId + "/rate/4")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));

        mockMvc.perform(
                        patch("/favorites-api/v1/beers/" + stringBeerId + "/rate/7")
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));

        mockMvc.perform(
                        delete("/favorites-api/v1/beers/" + stringBeerId)
                                .header("userId", userIdString)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + userIdString));
    }

    /**
     * Validate EndPoint if header UserID is not exist
     *
     * @throws Exception exception
     */
    @Test
    void testAllEP_WhenNotExistUserId_ShouldReturnStatusBadRequest() throws Exception {
        mockMvc.perform(
                        get("/favorites-api/v1/beers")
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Required request header 'userId' for method parameter type UUID is not present"));

        mockMvc.perform(
                        get("/favorites-api/v1/beers/" + stringBeerId)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Required request header 'userId' for method parameter type UUID is not present"));

        mockMvc.perform(
                        post("/favorites-api/v1/beers")
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Required request body is missing")));

        mockMvc.perform(
                        patch("/favorites-api/v1/beers/" + stringBeerId)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Required request header 'userId' for method parameter type UUID is not present"));

        mockMvc.perform(
                        patch("/favorites-api/v1/beers/" + stringBeerId + "/rate/4")
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Required request header 'userId' for method parameter type UUID is not present"));

        mockMvc.perform(
                        delete("/favorites-api/v1/beers/" + stringBeerId)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Required request header 'userId' for method parameter type UUID is not present"));
    }
}