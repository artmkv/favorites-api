package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoriteBeerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * interface beer service
 */
public interface BeerService {

    List<FavoriteBeerDto> getBeerByRate(Integer userId, Integer[] rate, Pageable pageable);

    boolean saveFavoriteBeer();

    boolean updateFavoriteBeer();

    boolean deleteFavoriteBeer(UUID id);
}
