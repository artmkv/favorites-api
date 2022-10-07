package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.exceptions.BeerExistsException;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerAndEntityBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test {@link BeerServiceImpl}
 */
class BeerServiceImplTest extends AbstractServiceTest {

    /**
     * @see BeerService
     */
    @Autowired
    private BeerService beerService;

    /**
     * @see BeerRepository
     */
    @MockBean
    private BeerRepository beerRepository;

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
     * Test {@link BeerService#getBeerById(UUID, UUID)}
     */
    @Test
    void testGetBeerById_ShouldReturnFavoritesBeer() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 4, "Beer");
        Optional<FavoritesBeer> optional = Optional.of(beer);
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        FavoritesBeerDto actual = beerService.getBeerById(userIdUUID, beer.getId());
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test {@link BeerService#getBeerById(UUID, UUID)}
     */
    @Test
    void testGetBeerById_ShouldThrowBeerEntityNotFoundException() {
        UUID beerId = UUID.randomUUID();
        Optional<FavoritesBeer> optional = Optional.empty();

        when(beerRepository.findOneBeerById(userIdUUID, beerId)).thenReturn(optional);

        assertThrows(BeerEntityNotFoundException.class, () -> beerService.getBeerById(userIdUUID, beerId));
    }

    /**
     * Test method {@link BeerService#getAllBeersByRate(GetBeerRequestDto)}
     */
    @Test
    void testGetAllBeersByRate_WithRateAndWithDefaultPageable_ShouldReturnPageOfFavoritesBeer() {
        Integer[] rate = {1, 2, 3, 4, 5};
        Pageable pageable = getDefaultPageable();
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        List<FavoritesBeer> list = createListOfFavoritesBeer();
        Page<FavoritesBeer> page = new PageImpl<>(list, pageable, list.size());
        Page<FavoritesBeerDto> expected = page.map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);

        when(beerRepository.findAllBySetRatesWithPagination(requestDto, requestDto.getPageable())).thenReturn(page);

        Page<FavoritesBeerDto> actual = beerService.getAllBeersByRate(requestDto);
        assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerService#getAllBeersByRate(GetBeerRequestDto)}
     */
    @Test
    void testGetAllBeersByRate_WithRateAndWithDefaultPageable_ShouldReturnEmptyPage() {
        Integer[] rate = {1, 2, 3, 4, 5};
        Pageable pageable = getDefaultPageable();
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = Page.empty(pageable);

        when(beerRepository.findAllBySetRatesWithPagination(requestDto, requestDto.getPageable())).thenReturn(page);

        Page<FavoritesBeerDto> actual = beerService.getAllBeersByRate(requestDto);
        assertTrue(actual.isEmpty());
    }

    /**
     * Test method {@link BeerService#getAllBeersByRate(GetBeerRequestDto)}
     */
    @Test
    void testGetAllBeersByRate_WithoutRateAndWithDefaultPageable_ShouldReturnPageOfFavoritesBeer() {
        Integer[] rate = {};
        Pageable pageable = getDefaultPageable();
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        List<FavoritesBeer> list = createListOfFavoritesBeer();
        Page<FavoritesBeer> page = new PageImpl<>(list, pageable, list.size());
        Page<FavoritesBeerDto> expected = page.map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);

        when(beerRepository.findAllWithPagination(requestDto, requestDto.getPageable())).thenReturn(page);

        Page<FavoritesBeerDto> actual = beerService.getAllBeersByRate(requestDto);
        assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerService#getAllBeersByRate(GetBeerRequestDto)}
     */
    @Test
    void testGetAllBeersByRate_WithoutRateAndWithDefaultPageable_ShouldReturnEmptyPageOfFavoritesBeer() {
        Integer[] rate = {};
        Pageable pageable = getDefaultPageable();
        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(userIdUUID, rate, pageable);
        Page<FavoritesBeer> page = Page.empty(pageable);

        when(beerRepository.findAllWithPagination(requestDto, requestDto.getPageable())).thenReturn(page);

        Page<FavoritesBeerDto> actual = beerService.getAllBeersByRate(requestDto);
        assertTrue(actual.isEmpty());
    }

    /**
     * Test method {@link BeerService#updateRateFavoritesBeer(UpdateBeerRequestDto)}
     */
    @Test
    void testUpdateRateFavoritesBeer_WithRequest_ShouldReturnFavoritesBeer() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, beer.getId(), request);
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        FavoritesBeerDto actual = beerService.updateRateFavoritesBeer(requestDto);
        assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerService#updateRateFavoritesBeer(UpdateBeerRequestDto)}
     */
    @Test
    void testUpdateRateFavoritesBeer_WithRequest_ShouldReturnException() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, beer.getId(), request);
        Optional<FavoritesBeer> optional = Optional.empty();

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        assertThrows(BeerEntityNotFoundException.class, () -> beerService.updateFavoriteBeer(requestDto));
    }

    /**
     * Test method {@link BeerService#updateFavoriteBeer(UpdateBeerRequestDto)}
     */
    @Test
    void testUpdateFavoriteBeer_ShouldReturnFavoriteBeer() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, beer.getId(), request);
        Optional<FavoritesBeer> optional = Optional.of(beer);
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        FavoritesBeerDto actual = beerService.updateFavoriteBeer(requestDto);
        assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerService#updateFavoriteBeer(UpdateBeerRequestDto)}
     */
    @Test
    void testUpdateFavoriteBeer_ShouldReturnException() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        UpdateBeerRequestDto requestDto = RequestBeerConverter.convertToUpdateRequestDto(userIdUUID, beer.getId(), request);
        Optional<FavoritesBeer> optional = Optional.empty();

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        assertThrows(BeerEntityNotFoundException.class, () -> beerService.updateFavoriteBeer(requestDto));
    }

    /**
     * Test method {@link BeerService#saveFavoriteBeer(SaveBeerRequestDto)}
     */
    @Test
    void testSaveFavoriteBeer_ShouldReturnFavoritesBeer() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 4, "Beer");
        SaveFavoritesBeerRequest request = createSaveBeerRequest(beer);
        SaveBeerRequestDto requestDto = RequestBeerConverter.convertToSaveRequestDto(userIdUUID, request);
        FavoritesBeer beerToSave = RequestBeerAndEntityBeerConverter.convertSaveRequestToFavoritesBeer(requestDto);
        Optional<FavoritesBeer> optional = Optional.empty();
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);

        when(beerRepository.findByUserAndBeer(userIdUUID, beer.getForeignBeerApiId())).thenReturn(optional);
        when(beerRepository.save(beerToSave)).thenReturn(beer);

        FavoritesBeerDto actual = beerService.saveFavoriteBeer(requestDto);
        assertEquals(expected, actual);
    }

    /**
     * Test method {@link BeerService#saveFavoriteBeer(SaveBeerRequestDto)}
     */
    @Test
    void testSaveFavoriteBeer_ShouldReturnException() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 4, "Beer");
        SaveFavoritesBeerRequest request = createSaveBeerRequest(beer);
        SaveBeerRequestDto requestDto = RequestBeerConverter.convertToSaveRequestDto(userIdUUID, request);
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findByUserAndBeer(userIdUUID, beer.getForeignBeerApiId())).thenReturn(optional);

        assertThrows(BeerExistsException.class, () -> beerService.saveFavoriteBeer(requestDto));
    }

    /**
     * Test method {@link BeerService#deleteFavoriteBeer(UUID, UUID)}
     */
    @Test
    void testDeleteFavoriteBeer() {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        beerService.deleteFavoriteBeer(userIdUUID, beer.getId());
        verify(beerRepository, times(1)).deleteOne(userIdUUID, beer.getId());
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @param beerId beerId
     * @param userId userId
     * @param rate   rate
     * @param name   name
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createFavoritesBeer(Long beerId, UUID userId, Integer rate, String name) {

        FavoritesBeer beer = new FavoritesBeer();
        beer.setId(UUID.randomUUID());
        beer.setForeignBeerApiId(beerId);
        beer.setUserId(userId);
        beer.setRate(rate);
        beer.setName(name);
        beer.setAbv(1.0);
        beer.setEbc(2.0);
        beer.setIbu(3.0);
        return beer;
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

    /**
     * Create {@link SaveFavoritesBeerRequest}
     *
     * @param beer {@link FavoritesBeer}
     * @return {@link SaveFavoritesBeerRequest}
     */
    private SaveFavoritesBeerRequest createSaveBeerRequest(FavoritesBeer beer) {
        SaveFavoritesBeerRequest request = new SaveFavoritesBeerRequest();
        request.setForeignBeerApiId(beer.getForeignBeerApiId());
        request.setName(beer.getName());
        request.setRate(beer.getRate());
        request.setAbv(beer.getAbv());
        request.setEbc(beer.getEbc());
        request.setIbu(beer.getIbu());
        return request;
    }

    /**
     * Create List of FavoritesBeer
     *
     * @return List of {@link FavoritesBeer}
     */
    private List<FavoritesBeer> createListOfFavoritesBeer() {
        List<FavoritesBeer> list = new ArrayList<>();
        list.add(createFavoritesBeer(10L, userIdUUID, 3, "Beer1"));
        list.add(createFavoritesBeer(11L, userIdUUID, 4, "Beer2"));
        list.add(createFavoritesBeer(12L, userIdUUID, 5, "Beer3"));
        list.add(createFavoritesBeer(13L, userIdUUID, 5, "Beer4"));
        list.add(createFavoritesBeer(14L, userIdUUID, 4, "Beer5"));
        return list;
    }

    /**
     * Get default Pageable
     *
     * @return {@link Pageable}
     */
    private Pageable getDefaultPageable() {
        return PageRequest.of(DEFAULT_PAGEABLE_PAGE, DEFAULT_PAGEABLE_SIZE, Sort.by(DEFAULT_PAGEABLE_SORT));
    }
}