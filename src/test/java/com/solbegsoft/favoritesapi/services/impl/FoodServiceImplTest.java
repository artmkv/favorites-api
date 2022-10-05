package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import com.solbegsoft.favoritesapi.exceptions.FoodExistsException;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.services.FoodService;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import com.solbegsoft.favoritesapi.utils.RequestFoodConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test {@link FoodService}
 */
class FoodServiceImplTest extends AbstractServiceTest {

    /**
     * @see FoodService
     */
    @MockBean
    private FoodRepository foodRepository;

    /**
     * @see FoodService
     */
    @Autowired
    private FoodService foodService;

    /**
     * Test Method {@link FoodService#getListOfFoodByBeerId(UUID, Long)}
     */
    @Test
    void testGetListOfFoodByBeerId_ShouldReturnListFavoritesFood() {
        Long foreignBeerId = 10L;
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, foreignBeerId, "Food", 3));
        list.add(createFavoritesFood(userIdUUID, foreignBeerId, "Burger", 4));
        List<FavoritesFoodDto> expectedList = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(list);

        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, foreignBeerId)).thenReturn(list);

        List<FavoritesFoodDto> actualList = foodService.getListOfFoodByBeerId(userIdUUID, foreignBeerId);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test Method {@link FoodService#getListOfFoodByBeerId(UUID, Long)}
     */
    @Test
    void testGetListOfFoodByBeerId_ShouldReturnEmptyList() {
        Long foreignBeerId = 10L;
        List<FavoritesFood> list = Collections.emptyList();

        when(foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, foreignBeerId)).thenReturn(list);

        List<FavoritesFoodDto> actualList = foodService.getListOfFoodByBeerId(userIdUUID, foreignBeerId);
        assertTrue(actualList.isEmpty());

    }

    /**
     * Test Method {@link FoodService#getListOfFoodByString(GetFoodRequestDto)}
     */
    @ParameterizedTest
    @ValueSource(strings = {"zza", "iz", "pi"})
    void testGetListOfFoodByString_ShouldReturnList(String searchString) {
        List<FavoritesFood> list = new ArrayList<>();
        list.add(createFavoritesFood(userIdUUID, 1L, "Pizza", 5));
        list.add(createFavoritesFood(userIdUUID, 2L, "Burger and pizza", 3));
        List<FavoritesFoodDto> expectedList = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(list);
        GetFoodRequestDto requestDto = RequestFoodConverter.convertToGetFoodRequestDto(userIdUUID, searchString);

        when(foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString)).thenReturn(list);

        List<FavoritesFoodDto> actualList = foodService.getListOfFoodByString(requestDto);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test Method {@link FoodService#getListOfFoodByString(GetFoodRequestDto)}
     */
    @ParameterizedTest
    @EmptySource
    void testGetListOfFoodByString_WithEmptyString_ShouldReturnEmptyList(String searchString) {
        List<FavoritesFood> list = Collections.emptyList();
        List<FavoritesFoodDto> expectedList = FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(list);
        GetFoodRequestDto requestDto = RequestFoodConverter.convertToGetFoodRequestDto(userIdUUID, searchString);

        when(foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString)).thenReturn(list);
        when(foodRepository.findAllFavoritesFood(userIdUUID)).thenReturn(list);

        List<FavoritesFoodDto> actualList = foodService.getListOfFoodByString(requestDto);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test with correct request Method {@link FoodService#saveOneFavoritesFood(FavoritesFoodDto)}
     */
    @Test
    void testSaveOneFavoritesFood_WithCorrectRequest_ShouldReturnFavoritesFood() {
        FavoritesFoodDto expected = createFavoritesFoodDto(userIdUUID, 12L, "Burger", 4);
        FavoritesFood food = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(expected);

        when(foodRepository.save(food)).thenReturn(food);

        FavoritesFoodDto actual = foodService.saveOneFavoritesFood(expected);
        assertEquals(expected, actual);
    }

    /**
     * Test with correct request Method {@link FoodService#saveOneFavoritesFood(FavoritesFoodDto)}
     */
    @Test
    void testSaveOneFavoritesFood_WithCorrectRequest_ShouldReturnException() {
        FavoritesFoodDto expected = createFavoritesFoodDto(userIdUUID, 12L, "Burger", 4);
        FavoritesFood food = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(expected);

        when(foodRepository.save(food)).thenThrow(new FoodExistsException(ExceptionMessageCodes.FOOD_ALREADY_EXIST));

        assertThrows(RuntimeException.class, () -> foodService.saveOneFavoritesFood(expected));
    }

    /**
     * Test Method {@link FoodService#deleteFavoritesFood(UUID, UUID)}
     */
    @Test
    void testDeleteFavoritesFood() {
        UUID beerId = UUID.randomUUID();
        foodService.deleteFavoritesFood(userIdUUID, beerId);
        verify(foodRepository, times(1)).deleteOne(userIdUUID, beerId);
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @param beerId beerId
     * @param userId userId
     * @param rate   rate
     * @param text   description
     * @return {@link FavoritesBeer}
     */
    private FavoritesFoodDto createFavoritesFoodDto(UUID userId, Long beerId, String text, Integer rate) {
        FavoritesFoodDto food = new FavoritesFoodDto();
        food.setId(UUID.randomUUID());
        food.setForeignBeerApiId(beerId);
        food.setUserId(userId);
        food.setRate(rate);
        food.setText(text);

        return food;
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @param beerId beerId
     * @param userId userId
     * @param rate   rate
     * @param text   description
     * @return {@link FavoritesBeer}
     */
    private FavoritesFood createFavoritesFood(UUID userId, Long beerId, String text, Integer rate) {
        FavoritesFood food = new FavoritesFood();
        food.setId(UUID.randomUUID());
        food.setForeignBeerApiId(beerId);
        food.setUserId(userId);
        food.setRate(rate);
        food.setText(text);

        return food;
    }
}