package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
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
    FavoritesBeerDto getBeerById(Long userId, UUID beerId);

    /**
     * Get Favorites Beers with pageable by rating
     *
     * @param getRequestDto request DTO
     * @return {@link  Page} of Favorites Beer
     */
    Page<FavoritesBeerDto> getAllBeersByRate(GetRequestDto getRequestDto);

    /**
     * Update Favorite Beer
     *
     * @param requestDto request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto updateFavoriteBeer(UpdateRequestDto requestDto);

    /**
     * Update Rate of Favorites Beer
     *
     * @param requestDto Update Request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto updateRateFavoritesBeer(UpdateRequestDto requestDto);

    /**
     * Delete Favorites Beer
     *
     * @param userId User ID
     * @param beerId Beer ID
     */
    void deleteFavoriteBeer(Long userId, UUID beerId);

    /**
     * Save Favorites Beer
     *
     * @param requestDto Request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto saveFavoriteBeer(SaveRequestDto requestDto);
}
