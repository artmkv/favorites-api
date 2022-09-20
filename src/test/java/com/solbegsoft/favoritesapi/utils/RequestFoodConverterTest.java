package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Test for {@link RequestFoodConverter}
 */
@SpringBootTest
class RequestFoodConverterTest {

    /**
     * Test method getFoodDtoFromRequest
     */
    @Test
    void testGetFoodDtoFromRequest() {
        UUID userId = UUID.randomUUID();

        SaveFavoritesFoodRequest request = new SaveFavoritesFoodRequest();
        request.setRate(4);
        request.setText("A");
        request.setForeignBeerApiId(10L);

        FavoritesFoodDto actual = RequestFoodConverter.getFoodDtoFromRequest(userId, request);

        Assertions.assertEquals(actual.getText(), request.getText());
        Assertions.assertEquals(actual.getUserId(), userId);
        Assertions.assertEquals(actual.getForeignBeerApiId(), request.getForeignBeerApiId());
        Assertions.assertEquals(actual.getRate(), request.getRate());
        Assertions.assertNull(actual.getId());

    }

    /**
     * Test for method convertToGetFoodRequestDto
     *
     * @param search search String
     */
    @ParameterizedTest
    @ValueSource(strings = {"Pizza", "", "___"})
    @NullSource
    void testConvertToGetFoodRequestDto(String search) {
        UUID userId = UUID.randomUUID();

        GetFoodRequestDto actual = RequestFoodConverter.convertToGetFoodRequestDto(userId, search);

        Assertions.assertEquals(actual.getUserId(), userId);
        Assertions.assertEquals(actual.getText(), search);
        Assertions.assertNotNull(actual);
    }
}