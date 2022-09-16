package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import com.solbegsoft.favoritesapi.repositories.FoodRepository;
import com.solbegsoft.favoritesapi.services.FoodService;
import com.solbegsoft.favoritesapi.utils.FavoritesFoodConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Food service implemented {@link FoodService}
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
    public List<FavoritesFoodDto> getListOfFoodByBeerId(UUID userId, Long foreignBeerId) {

        return FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(repository.findAllFavoritesFood(userId));
    }

    @Override
    public List<FavoritesFoodDto> getListOfFoodByString(GetFoodRequestDto dto) {
        UUID userId = dto.getUserId();
        if (isExistSearchString(dto)) {
            String searchString = "%" + dto.getText().toLowerCase() + "%";
            return FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(repository.findAllFavoritesFoodByString(userId, searchString));
        }
        return FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(repository.findAllFavoritesFood(userId));
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

    /**
     * Is exist Search string in RequestDto
     *
     * @param dto {@link GetFoodRequestDto}
     * @return boolean
     */
    private boolean isExistSearchString(GetFoodRequestDto dto) {
        return Objects.nonNull(dto.getText());
    }
}
