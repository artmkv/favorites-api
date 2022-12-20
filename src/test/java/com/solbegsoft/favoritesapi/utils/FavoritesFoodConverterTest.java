package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test {@link FavoritesFoodConverter}
 */
class FavoritesFoodConverterTest {

    /**
     * Test method getDtoFromFavoritesFood
     */
    @Test
    void getDtoFromFavoritesFood() {
        FavoritesFood actual = createFavoritesFood();
        FavoritesFoodDto expected = FavoritesFoodConverter.INSTANCE.getDtoFromFavoritesFood(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());

    }

    /**
     * Test method getFavoritesFoodFromDto
     */
    @Test
    void getFavoritesFoodFromDto() {

        FavoritesFoodDto actual = new FavoritesFoodDto();
        actual.setId(UUID.randomUUID());
        actual.setUserId(UUID.randomUUID());
        actual.setForeignBeerApiId(144L);
        actual.setRate(2);
        actual.setText("A");

        FavoritesFood expected = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());

    }

    /**
     * Test method getListDtoFromListFavoritesFood
     */
    @Test
    void getListDtoFromListFavoritesFood() {
        List<FavoritesFood> actual = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            actual.add(createFavoritesFood());
        }
        List<FavoritesFoodDto> expected = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(actual);

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getUserId(), actual.get(i).getUserId());
            assertEquals(expected.get(i).getRate(), actual.get(i).getRate());
            assertEquals(expected.get(i).getText(), actual.get(i).getText());
            assertEquals(expected.get(i).getForeignBeerApiId(), actual.get(i).getForeignBeerApiId());
        }

    }

    /**
     * Create {@link FavoritesFood}
     *
     * @return {@link FavoritesFood}
     */
    private FavoritesFood createFavoritesFood() {
        FavoritesFood actual = new FavoritesFood();
        actual.setId(UUID.randomUUID());
        actual.setUserId(UUID.randomUUID());
        actual.setForeignBeerApiId(144L);
        actual.setRate(2);
        actual.setText("A");
        return actual;
    }
}