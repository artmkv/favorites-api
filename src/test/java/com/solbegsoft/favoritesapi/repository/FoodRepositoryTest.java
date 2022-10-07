package com.solbegsoft.favoritesapi.repository;


import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test {@link  FoodRepository}
 */
class FoodRepositoryTest extends AbstractRepositoryTest {

    /**
     * @see FoodRepository
     */
    @Autowired
    private FoodRepository foodRepository;

    void intit(){

    }
    /**
     * Test {@link FoodRepository#findAllFavoritesFood(UUID)}
     * Should Return List With Food
     */
    @Test
    @Transactional
    void testFindAllFavoritesFood_ShouldReturnListWithFood() {
        insertFoods();
        List<FavoritesFood> result = foodRepository.findAllFavoritesFood(userIdUUID);
        assertEquals(result.size(), 6);
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFood(UUID)}
     * Should Return Empty List
     */
    @Test
    @Transactional
    void testFindAllFavoritesFood_ShouldReturnEmptyList() {
        List<FavoritesFood> result = foodRepository.findAllFavoritesFood(userIdUUID);
        assertTrue(result.isEmpty());
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFood(UUID)}
     * Should Return Empty List
     */
    @ParameterizedTest
    @NullSource
    @Transactional
    void testFindAllFavoritesFood_WhenParametersIsNull_ShouldReturnException(UUID userId) {
        List<FavoritesFood> result = foodRepository.findAllFavoritesFood(userId);
        assertTrue(result.isEmpty());
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByBeerId(UUID, Long)}
     */
    @Test
    @Transactional
    void tesFindAllFavoritesFoodByBeerId_ShouldReturnList() {
        insertFoods();
        List<FavoritesFood> resultOne = foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, 10L);
        List<FavoritesFood> resultThree = foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, 12L);

        assertEquals(resultOne.size(), 1);
        assertEquals(resultThree.size(), 3);
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByBeerId(UUID, Long)}
     */
    @Test
    @Transactional
    void tesFindAllFavoritesFoodByBeerId_ShouldReturnEmptyList() {
        List<FavoritesFood> resultOne = foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, 10L);
        List<FavoritesFood> resultThree = foodRepository.findAllFavoritesFoodByBeerId(userIdUUID, 12L);

        assertTrue(resultOne.isEmpty());
        assertTrue(resultThree.isEmpty());
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByBeerId(UUID, Long)
     * Should Return Empty List
     */
    @Test
    @Transactional
    void testFindAllFavoritesFoodByBeerId_WhenParametersNull_ShouldReturnEmptyList() {
        List<FavoritesFood> resultOne = foodRepository.findAllFavoritesFoodByBeerId(null, null);
        List<FavoritesFood> resultThree = foodRepository.findAllFavoritesFoodByBeerId(null, null);

        assertTrue(resultOne.isEmpty());
        assertTrue(resultThree.isEmpty());
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByString(UUID, String)}
     * Should Return List
     */
    @ParameterizedTest
    @ValueSource(strings = {"food", "Foo", "oo", "cy"})
    @Transactional
    void testFindAllFavoritesFoodByString_ShouldReturnList(String searchString) {
        insertFoods();
        List<FavoritesFood> result = foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString);
        assertEquals(result.size(), 5);
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByString(UUID, String)}
     * Should Return List
     */
    @ParameterizedTest
    @ValueSource(strings = {"ZZa", "pizz", "za"})
    void testFindAllFavoritesFoodByString_ShouldReturnListWithItems(String searchString) {
        insertFoods();
        List<FavoritesFood> result = foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString);
        assertEquals(result.size(), 1);
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByString(UUID, String)}
     * Should Return List
     */
    @ParameterizedTest
    @ValueSource(strings = {"gdbdfkbdba", "!1ed3r", "_&343rf"})
    void testFindAllFavoritesFoodByString_WithSearchString_ShouldReturnEmptyList(String searchString) {
        insertFoods();
        List<FavoritesFood> result = foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString);
        assertTrue(result.isEmpty());
    }

    /**
     * Test {@link FoodRepository#findAllFavoritesFoodByString(UUID, String)}
     * Should Return List
     */
    @ParameterizedTest
    @EmptySource
    @Transactional
    void testFindAllFavoritesFoodByString_WithEmptyString_ShouldReturnFullList(String searchString) {
        insertFoods();
        List<FavoritesFood> result = foodRepository.findAllFavoritesFoodByString(userIdUUID, searchString);
        assertEquals(result.size(), 6);
    }

    /**
     * Test {@link FoodRepository#deleteOne(UUID, UUID)}
     * Finished without Exception
     */
    @Test
    void testDeleteOne_WithCorrectParams() {
        insertFoods();
        UUID beerId = UUID.randomUUID();
        assertDoesNotThrow(() -> foodRepository.deleteOne(userIdUUID, beerId));
    }

    /**
     * Test {@link FoodRepository#deleteOne(UUID, UUID)}
     * Null params
     * Finished without Exception
     */
    @Test
    void testDeleteOne_WithNull_ShouldCompleteWithoutException() {
        insertFoods();
        assertDoesNotThrow(() -> foodRepository.deleteOne(null, null));
    }

    /**
     * Insert Foods to Repository
     */
    private void insertFoods() {
        foodRepository.save(createFood(10L, 3));
        foodRepository.save(createFood(11L, 4));
        foodRepository.save(createFood(12L, 5));
        foodRepository.save(createFood(12L, 3));
        foodRepository.save(createFood(12L, 4));
        foodRepository.save(createFood(22L, "Pizza with cheese", 1));
    }

    /**
     * Create Favorites Food
     *
     * @param foreignBeerId foreign Beer ID
     * @param rate          rate of Favorites Beer
     * @return {@link FavoritesFood}
     */
    private FavoritesFood createFood(Long foreignBeerId, Integer rate) {

        return FavoritesFood.builder()
                .id(UUID.randomUUID())
                .foreignBeerApiId(foreignBeerId)
                .userId(userIdUUID)
                .text("Spicy food" + foreignBeerId)
                .rate(rate)
                .build();
    }

    /**
     * Create Favorites Food
     *
     * @param foreignBeerId foreign Beer ID
     * @param rate          rate of Favorites Beer
     * @param text          description of Food
     * @return {@link FavoritesFood}
     */
    private FavoritesFood createFood(Long foreignBeerId, String text, Integer rate) {

        return FavoritesFood.builder()
                .id(UUID.randomUUID())
                .foreignBeerApiId(foreignBeerId)
                .userId(userIdUUID)
                .text(text)
                .rate(rate)
                .build();
    }
}
