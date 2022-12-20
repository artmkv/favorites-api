package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.configurations.exceptions.ExceptionMessageCodes;
import com.solbegsoft.favoritesapi.exceptions.FoodExistsException;
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
    private final FoodRepository foodRepository;

    @Override
    public List<FavoritesFoodDto> getListOfFoodByBeerId(UUID userId, Long foreignBeerId) {

        List<FavoritesFood> resultList = foodRepository.findAllFavoritesFoodByBeerId(userId, foreignBeerId);
        return FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(resultList);
    }

    @Override
    public List<FavoritesFoodDto> getListOfFoodByString(GetFoodRequestDto dto) {
        UUID userId = dto.getUserId();
        List<FavoritesFood> resultList;
        if (isExistSearchString(dto)) {
            resultList = foodRepository.findAllFavoritesFoodByString(userId, dto.getText());
        } else {
            resultList = foodRepository.findAllFavoritesFood(userId);
        }
        return FavoritesFoodConverter.INSTANCE.getListDtoFromListFavoritesFood(resultList);
    }

    @Override
    public FavoritesFoodDto saveOneFavoritesFood(FavoritesFoodDto dto) {
        FavoritesFood entity = FavoritesFoodConverter.INSTANCE.getFavoritesFoodFromDto(dto);
        try {
            FavoritesFood save = foodRepository.save(entity);
            return FavoritesFoodConverter.INSTANCE.getDtoFromFavoritesFood(save);
        } catch (RuntimeException e) {
            throw new FoodExistsException(ExceptionMessageCodes.FOOD_ALREADY_EXIST);
        }
    }

    @Override
    public void deleteFavoritesFood(UUID userId, UUID id) {

        foodRepository.deleteOne(userId, id);
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
