package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.RequestDto;

import java.util.List;
import java.util.UUID;

/**
 * interface beer service
 */
public interface BeerService {

    FavoritesBeerDto getBeerById(Integer userId, UUID beerId);

    List<FavoritesBeerDto> getAllBeersByRate(RequestDto requestDto);

    FavoritesBeerDto updateFavoriteBeer(RequestDto requestDto);

    void deleteFavoriteBeer(Integer userId, UUID beerId);

    FavoritesBeerDto saveFavoriteBeer(RequestDto requestDto);
}
