package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation Food Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    /**
     * @see FoodRepository
     */
    private final FoodRepository repository;

    @Override
    public List<FavoritesFoodDto> getListOfFood(UUID userId) {

        return repository.getAllFavoritesFood(userId).stream()
                .map(FavoritesFoodConverter.INSTANCE::getDtoFromFavoritesFood)
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoritesFoodDto> getListOfFoodByString(UUID userId, String maybeFood) {
        return new ArrayList<>();
    }

    @Override
    public FavoritesFoodDto saveOneFavoritesFood(FavoritesFoodDto dto) {

        return new FavoritesFoodDto();
    }

    @Override
    public void deleteFavoritesFood(UUID userId, UUID id) {

    }
}
