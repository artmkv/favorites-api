package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

/**
 * Service for favorites beer
 */
public interface BeerService {

    /**
     * Get one Favorites Beer
     *
     * @param userId User ID
     * @param beerId Beer ID
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto getBeerById(UUID userId, UUID beerId);

    /**
     * Get Favorites Beers with pageable by rating
     *
     * @param getRequestDto request DTO
     * @return {@link  Page} of Favorites Beer
     */
    Page<FavoritesBeerDto> getAllBeersByRate(GetBeerRequestDto getRequestDto);

    /**
     * Update Favorite Beer
     *
     * @param requestDto request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto updateFavoriteBeer(UpdateBeerRequestDto requestDto);

    /**
     * Update Rate of Favorites Beer
     *
     * @param requestDto Update Request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto updateRateFavoritesBeer(UpdateBeerRequestDto requestDto);

    /**
     * Delete Favorites Beer
     *
     * @param userId User ID
     * @param beerId Beer ID
     */
    void deleteFavoriteBeer(UUID userId, UUID beerId);

    /**
     * Save Favorites Beer
     *
     * @param requestDto Request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto saveFavoriteBeer(SaveBeerRequestDto requestDto);
}
