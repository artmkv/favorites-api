package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test {@link BeerServiceImpl}
 */
class BeerServiceImplTest extends AbstractServiceTest {

    @Autowired
    private BeerService beerService;

    @MockBean
    private BeerRepository beerRepository;

    /**
     * Test {@link BeerService#getBeerById(UUID, UUID)}
     */
    @Test
    void getBeerById_ShouldReturnFavoritesBeer() {
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
    void getBeerById_ShouldThrowBeerEntityNotFoundException() {
        UUID beerId = UUID.randomUUID();
        Optional<FavoritesBeer> optional = Optional.empty();

        when(beerRepository.findOneBeerById(userIdUUID, beerId)).thenReturn(optional);

        assertThrows(BeerEntityNotFoundException.class, () -> {
            beerService.getBeerById(userIdUUID, beerId);
        });
    }


    @Test
    void getAllBeersByRate() {

        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        Integer[] rate = {1, 5};

    }

    /**
     * Test method {@link BeerService#deleteFavoriteBeer(UUID, UUID)}
     */
    @Test
    void deleteFavoriteBeer() {
        UUID beerId = UUID.randomUUID();
        beerService.deleteFavoriteBeer(userIdUUID, beerId);
        verify(beerRepository, times(1)).deleteOne(userIdUUID, beerId);
    }

    @Test
    void updateRateFavoritesBeer() {

    }

    @Test
    void updateFavoriteBeer() {
    }

    @Test
    void saveFavoriteBeer() {
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
}