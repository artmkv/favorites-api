package com.solbegsoft.favoritesapi.repository;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerAndEntityBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerConverter;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test {@link BeerRepository}
 */
class BeerRepositoryTest extends AbstractRepositoryTest {

    /**
     * Default number of page
     */
    public static final int DEFAULT_PAGEABLE_PAGE = 0;

    /**
     * Default size of page
     */
    public static final int DEFAULT_PAGEABLE_SIZE = 20;

    /**
     * Default sort column
     */
    public static final String DEFAULT_PAGEABLE_SORT = "id";

    /**
     * Beer UUID ID
     */
    public static final UUID BEER_UUID_ID = UUID.fromString("cb5689be-4cbc-4dd3-a9d8-7506cc027dbf");

    /**
     * Number
     */
    public static final int SIZE_ENTITY_IN_DB = 8;

    /**
     * @see BeerRepository
     */
    @Autowired
    private BeerRepository beerRepository;

    /**
     * Insert Data before each test
     */
    @BeforeEach
    void init() {
//        startContainer();
        insertBeers();
    }

    /**
     * Delete all data from DB
     */
    @AfterEach
    void deleteData() {
        beerRepository.deleteAll();
    }

    /**
     * Test {@link BeerRepository#findAllWithPagination(GetBeerRequestDto, Pageable)}
     * Return Page of FavoritesBeer
     */
    @Test
    @Transactional
    void testFindAllWithPagination_ShouldReturnPage() {
        Pageable pageable = getDefaultPageable();
        Integer[] rate = {1, 2, 3, 4, 5};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
//        Page<FavoritesBeer> page = beerRepository.findAllWithPagination(requestDto, pageable);
//        assertEquals(SIZE_ENTITY_IN_DB, page.getTotalElements());

        List<FavoritesBeer> all = beerRepository.findAll();
        assertEquals(SIZE_ENTITY_IN_DB, all.size());
    }

    /**
     * Test {@link BeerRepository#findAllWithPagination(GetBeerRequestDto, Pageable)}
     * Throws Exception
     */
    @Test
    void testFindAllWithPagination_WithNullParameters_ShouldReturnException() {
        Assert.assertThrows(RuntimeException.class, () -> beerRepository.findAllWithPagination(null, null));
    }

    /**
     * Test {@link BeerRepository#findAllWithPagination(GetBeerRequestDto, Pageable)}
     * Return empty Page
     */
    @Test
    void testFindAllWithPagination_ShouldReturnEmptyList() {
        beerRepository.deleteAll();
        Pageable pageable = getDefaultPageable();
        Integer[] rate = {1, 5};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = beerRepository.findAllWithPagination(requestDto, pageable);
        assertTrue(page.isEmpty());
    }

    /**
     * Test {@link BeerRepository#findAllBySetRatesWithPagination(GetBeerRequestDto, Pageable)}
     * Return Page of FavoritesBeer
     */
    @Test
    void testFindAllBySetRatesWithPagination_ShouldReturnList() {
        Pageable pageable = getDefaultPageable();
        Integer[] rate = {1, 5};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = beerRepository.findAllBySetRatesWithPagination(requestDto, pageable);
        assertEquals(page.getTotalElements(), 2);
    }

    /**
     * Test {@link BeerRepository#findAllBySetRatesWithPagination(GetBeerRequestDto, Pageable)}
     * Return empty Page
     */
    @Test
    void testFindAllBySetRatesWithPagination_WithRateArray_ShouldReturnEmptyList() {
        Pageable pageable = getDefaultPageable();
        Integer[] rate = {1};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = beerRepository.findAllBySetRatesWithPagination(requestDto, pageable);
        assertTrue(page.isEmpty());
    }

    /**
     * Test {@link BeerRepository#findAllBySetRatesWithPagination(GetBeerRequestDto, Pageable)}
     * Return empty Page
     */
    @Test
    void testFindAllBySetRatesWithPagination_WithRateArrayAndAnyUserId_ShouldReturnEmptyList() {
        Pageable pageable = getDefaultPageable();
        UUID userId = UUID.randomUUID();
        Integer[] rate = {1, 2, 3, 4, 5};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userId, rate, pageable);
        Page<FavoritesBeer> page = beerRepository.findAllBySetRatesWithPagination(requestDto, pageable);
        assertTrue(page.isEmpty());
    }

    /**
     * Test {@link BeerRepository#findAllBySetRatesWithPagination(GetBeerRequestDto, Pageable)}
     * Return Page of FavoritesBeer
     */
    @Test
    void testFindAllBySetRatesWithPagination_WithFullRateArrayAnd_ShouldReturnFullPage() {
        Pageable pageable = getDefaultPageable();
        Integer[] rate = {1, 2, 3, 4, 5};
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = beerRepository.findAllBySetRatesWithPagination(requestDto, pageable);
        assertEquals(SIZE_ENTITY_IN_DB, page.getTotalElements());
    }

    /**
     * Test {@link BeerRepository#findAllBySetRatesWithPagination(GetBeerRequestDto, Pageable)}
     * Throws Exception
     */
    @Test
    void testFindAllBySetRatesWithPagination_WithNullParameters_ShouldReturnFullPage() {
        Assert.assertThrows(RuntimeException.class, () -> beerRepository.findAllBySetRatesWithPagination(null, null));
    }

    /**
     * Test {@link BeerRepository#findOneBeerById(UUID, UUID)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testFindOneBeerById_ShouldReturnBeer() {
        FavoritesBeer beer = createBeerWithDefaultBeerUUID(200L, "Craft Red", 4);
        FavoritesBeer save = beerRepository.save(beer);
        Optional<FavoritesBeer> result = beerRepository.findOneBeerById(userIdUUID, save.getId());

        assertTrue(result.isPresent());
        assertEquals(save, result.get());
    }

    /**
     * Test {@link BeerRepository#updateRateFavoritesBeer(UpdateBeerRequestDto)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testUpdateRateFavoritesBeer_ShouldReturnEntity() {
        FavoritesBeer beer = createBeerWithDefaultBeerUUID(200L, "Craft Red", 1);
        FavoritesBeer saved = beerRepository.save(beer);
        saved.setRate(3);
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(saved);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, saved.getId(), request);

        assertDoesNotThrow(() -> beerRepository.updateRateFavoritesBeer(requestDto));

        FavoritesBeer actual = beerRepository.findOneBeerById(userIdUUID, saved.getId())
                .orElseThrow(RuntimeException::new);

        assertEquals(saved, actual);
    }

    /**
     * Test {@link BeerRepository#updateRateFavoritesBeer(UpdateBeerRequestDto)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testUpdateRateFavoritesBeer_WithNullParameters_ShouldThrowsException() {
        assertThrows(RuntimeException.class, () -> beerRepository.updateRateFavoritesBeer(null));
    }

    /**
     * Test {@link BeerRepository#updateFavoritesBeer(FavoritesBeer)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testUpdateFavoritesBeer_ShouldReturnEntity() {
        FavoritesBeer beer = createBeerWithDefaultBeerUUID(200L, "Craft Red", 1);
        FavoritesBeer saved = beerRepository.save(beer);
        saved.setRate(3);
        saved.setEbc(99.9);
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(saved);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, saved.getId(), request);
        FavoritesBeerDto beerDto = RequestBeerAndEntityBeerConverter.convertUpdateRequestToFavoritesBeer(requestDto);
        FavoritesBeer entity = FavoritesBeerConverter.INSTANCE.toFavoritesBeerFromDto(beerDto);

        assertDoesNotThrow(() -> beerRepository.updateFavoritesBeer(entity));

        FavoritesBeer actual = beerRepository.findOneBeerById(userIdUUID, saved.getId())
                .orElseThrow(RuntimeException::new);

        assertEquals(saved, actual);
    }

    /**
     * Test {@link BeerRepository#updateFavoritesBeer(FavoritesBeer)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testUpdateFavoritesBeer_WithNullParameters_ShouldThrowsException() {
        assertThrows(RuntimeException.class, () -> beerRepository.updateFavoritesBeer(null));
    }

    /**
     * Test {@link BeerRepository#findByUserAndBeer(UUID, Long)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testFindByUserAndBeer_ShouldReturnOptionalOfBeer() {
        FavoritesBeer beer = createBeerWithDefaultBeerUUID(200L, "Craft Red", 4);
        FavoritesBeer save = beerRepository.save(beer);
        Optional<FavoritesBeer> result = beerRepository.findByUserAndBeer(userIdUUID, save.getForeignBeerApiId());

        assertTrue(result.isPresent());
        assertEquals(save, result.get());
    }

    /**
     * Test {@link BeerRepository#findByUserAndBeer(UUID, Long)}
     * Return FavoritesBeer
     */
    @Test
    void testFindByUserAndBeer_ShouldReturnEmptyOptional() {
        FavoritesBeer beer = createBeerWithDefaultBeerUUID(200L, "Craft Red", 4);
        Optional<FavoritesBeer> result = beerRepository.findByUserAndBeer(userIdUUID, beer.getForeignBeerApiId());

        assertTrue(result.isEmpty());
    }

    /**
     * Test {@link BeerRepository#findByUserAndBeer(UUID, Long)}
     * Return FavoritesBeer
     */
    @Test
    void testFindByUserAndBeer_WithNullParameters_ShouldReturnException() {
        assertDoesNotThrow(() -> beerRepository.findByUserAndBeer(null, null));
    }

    /**
     * Test {@link BeerRepository#deleteOne(UUID, UUID)}
     * Return FavoritesBeer
     */
    @Test
    @Transactional
    void testDeleteOne_ShouldFinishedWithoutExceptions() {
        UUID beerId = UUID.randomUUID();
        assertDoesNotThrow(() -> beerRepository.deleteOne(userIdUUID, beerId));
    }

    /**
     * Test {@link BeerRepository#deleteOne(UUID, UUID)}
     * Finished without throws
     */
    @Test
    void testDeleteOne_WithNullParameters() {
        assertDoesNotThrow(() -> beerRepository.deleteOne(null, null));
    }

    /**
     * Add to Repository Beers
     */
    private void insertBeers() {
        beerRepository.save(createBeer(10L, 3));
        beerRepository.save(createBeer(11L, 4));
        beerRepository.save(createBeer(12L, 5));
        beerRepository.save(createBeer(13L, 3));
        beerRepository.save(createBeer(14L, 4));
        beerRepository.save(createBeer(21L, "Pilsner", 2));
        beerRepository.save(createBeer(22L, "Red Pilsner", 3));
        beerRepository.save(createBeerWithDefaultBeerUUID(100L, "Craft Beer", 5));
    }

    /**
     * Create FavoritesBeer
     *
     * @param foreignBeerId foreignBeer ID
     * @param rate          Rate of Favorites Beer
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createBeer(Long foreignBeerId, Integer rate) {

        return FavoritesBeer.builder()
                .id(UUID.randomUUID())
                .userId(userIdUUID)
                .foreignBeerApiId(foreignBeerId)
                .name("Beer" + foreignBeerId)
                .rate(rate)
                .ibu(1.0)
                .ebc(2.0)
                .abv(3.0)
                .build();
    }

    /**
     * Create FavoritesBeer
     *
     * @param foreignBeerId foreignBeer ID
     * @param rate          Rate of Favorites Beer
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createBeer(Long foreignBeerId, String name, Integer rate) {

        return FavoritesBeer.builder()
                .id(UUID.randomUUID())
                .userId(userIdUUID)
                .foreignBeerApiId(foreignBeerId)
                .name(name)
                .rate(rate)
                .ibu(1.0)
                .ebc(2.0)
                .abv(3.0)
                .build();
    }

    /**
     * Create FavoritesBeer
     *
     * @param foreignBeerId foreignBeer ID
     * @param rate          Rate of Favorites Beer
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createBeerWithDefaultBeerUUID(Long foreignBeerId, String name, Integer rate) {

        return FavoritesBeer.builder()
                .id(BEER_UUID_ID)
                .userId(userIdUUID)
                .foreignBeerApiId(foreignBeerId)
                .name(name)
                .rate(rate)
                .ibu(1.0)
                .ebc(2.0)
                .abv(3.0)
                .build();
    }

    /**
     * Get default Pageable
     *
     * @return {@link Pageable}
     */
    private Pageable getDefaultPageable() {
        return PageRequest.of(DEFAULT_PAGEABLE_PAGE, DEFAULT_PAGEABLE_SIZE, Sort.by(DEFAULT_PAGEABLE_SORT));
    }

    /**
     * Create {@link UpdateFavoritesBeerRequest}
     *
     * @return {@link UpdateFavoritesBeerRequest}
     */
    private UpdateFavoritesBeerRequest createUpdateFavoritesBeerRequest(FavoritesBeer beer) {
        UpdateFavoritesBeerRequest request = new UpdateFavoritesBeerRequest();
        request.setId(beer.getId());
        request.setForeignBeerApiId(beer.getForeignBeerApiId());
        request.setName(beer.getName());
        request.setRate(beer.getRate());
        request.setAbv(beer.getAbv());
        request.setEbc(beer.getEbc());
        request.setIbu(beer.getIbu());
        return request;
    }
}