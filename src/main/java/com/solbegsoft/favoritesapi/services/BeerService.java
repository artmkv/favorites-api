package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.RequestDto;
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
     * Get All Favorites Beers with pageable
     *
     * @param userId User ID
     * @return {@link  Page} of Favorites Beer
     */
    Page<FavoritesBeerDto> getAllBeers(Long userId);

    /**
     * Get Favorites Beers with pageable by rating
     *
     * @param requestDto request DTO
     * @return {@link  Page} of Favorites Beer
     */
    Page<FavoritesBeerDto> getAllBeersByRate(RequestDto requestDto);

    /**
     * Update Favorite Beer
     *
     * @param requestDto request Dto
     * @return {@link FavoritesBeerDto}
     */
    FavoritesBeerDto updateFavoriteBeer(RequestDto requestDto);

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
    FavoritesBeerDto saveFavoriteBeer(RequestDto requestDto);
}
