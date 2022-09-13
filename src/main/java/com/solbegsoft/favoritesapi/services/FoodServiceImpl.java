package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation Food Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    /**
     * @see FoodRepository
     */
    private final FoodRepository repository;

    @Override
    public List<FavoritesFoodDto> getListOfFoodByString(GetFoodRequestDto dto) {

        UUID userId = dto.getUserId();
        if(Objects.nonNull(dto.getText())){
            String string = "%" + dto.getText().toLowerCase() + "%";

            return repository.getAllFavoritesFoodByString(userId, string)
                    .stream()
                    .map(FavoritesFoodConverter.INSTANCE::getDtoFromFavoritesFood)
                    .collect(Collectors.toList());
        }

        return repository.getAllFavoritesFood(userId).stream()
                .map(FavoritesFoodConverter.INSTANCE::getDtoFromFavoritesFood)
                .collect(Collectors.toList());
    }

    @Override
    public FavoritesFoodDto saveOneFavoritesFood(FavoritesFoodDto dto) {

        FavoritesFood entity = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(dto);
        FavoritesFood save = repository.save(entity);

        return FavoritesFoodConverter.INSTANCE.getDtoFromFavoritesFood(save);
    }

    @Override
    public void deleteFavoritesFood(UUID userId, UUID id) {

        repository.deleteOne(userId, id);
    }
}
