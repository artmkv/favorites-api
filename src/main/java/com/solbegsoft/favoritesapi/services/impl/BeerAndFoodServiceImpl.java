package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.exceptions.ExceptionMessagesConstant;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.services.BeerAndFoodService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Food And Beer Service
 */
@Repository
@RequiredArgsConstructor
public class BeerAndFoodServiceImpl implements BeerAndFoodService {

    /**
     * @see BeerRepository
     */
    private final BeerRepository beerRepository;

    /**
     * @see FoodRepository
     */
    private final FoodRepository foodRepository;

    /**
     * @see MessageUtils
     */
    private final MessageUtils messages;

    @Override
    public ResponseBeerWithFood getBeerByIdWithFood(UUID userId, UUID id) {

        FavoritesBeerDto beer = beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND)));

        List<FavoritesFoodDto> foods = foodRepository.getFavoritesFoodByUserIdAndBeerId(userId, beer.getForeignBeerApiId())
                .stream().map(FavoritesFoodConverter.INSTANCE::getDtoFromFavoritesFood)
                .collect(Collectors.toList());

        return new ResponseBeerWithFood(beer, foods);
    }
}
