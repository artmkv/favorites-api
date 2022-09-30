package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesFoodRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetFoodRequestDto;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.FoodService;
import com.solbegsoft.favoritesapi.utils.RequestFoodConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Favorites food controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("favorites-api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    /**
     * @see FoodService
     */
    private final FoodService foodService;

    /**
     * Get List of Food by text
     *
     * @param userId User ID
     * @param search text to search
     * @return List of {@link FavoritesFoodDto}
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<List<FavoritesFoodDto>> getFavoritesFoodByString(@RequestHeader UUID userId,
                                                                        @RequestParam(required = false) String search
    ) {
        log.info("#GET: Get all food by userId {} and by string {}", userId, search);
        GetFoodRequestDto requestDto = RequestFoodConverter.convertToGetFoodRequestDto(userId, search);
        List<FavoritesFoodDto> result = foodService.getListOfFoodByString(requestDto);
        log.info("#GET: Success get all food by userId {} and by string {}", userId, search);

        return new ResponseApi<>(result);
    }

    /**
     * Save Favorites Food
     *
     * @param userId  User ID
     * @param request {@link SaveFavoritesFoodRequest}
     * @return {@link FavoritesFoodDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<FavoritesFoodDto> saveFavoritesFood(@RequestHeader UUID userId,
                                                           @RequestBody @Valid SaveFavoritesFoodRequest request
    ) {
        log.info("#POST: Save food by userId {} and by beerId {}", userId, request.getForeignBeerApiId());
        FavoritesFoodDto favoritesFoodDto = RequestFoodConverter.getFoodDtoFromRequest(userId, request);
        FavoritesFoodDto result = foodService.saveOneFavoritesFood(favoritesFoodDto);
        log.info("#POST: Success save food by userId {} and by beerId {}", userId, request.getForeignBeerApiId());

        return new ResponseApi<>(result);
    }

    /**
     * Delete One Favorites Food
     *
     * @param id     ID of Favorites Food
     * @param userId User ID
     * @return boolean
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<Boolean> deleteFavoritesFood(@PathVariable("id") UUID id,
                                                    @RequestHeader UUID userId
    ) {

        log.info("#DELETE: userId {}, Id {}", userId, id);
        foodService.deleteFavoritesFood(userId, id);
        log.info("#DELETE: deleted success. UserId {}, Id {}", userId, id);

        return new ResponseApi<>(Boolean.TRUE);
    }
}