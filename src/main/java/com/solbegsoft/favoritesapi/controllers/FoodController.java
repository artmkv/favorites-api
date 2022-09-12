package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFoodRequest;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.FoodService;
import com.solbegsoft.favoritesapi.utils.RequestFoodConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * Get All Favorites Food
     *
     * @param userId User ID
     * @return List of Favorites Food
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<List<FavoritesFoodDto>> getListFavoritesFood(@RequestHeader UUID userId) {

        log.info("#GET: Get all foods by userId {}", userId);
        List<FavoritesFoodDto> result = foodService.getListOfFood(userId);
        log.info("#GET: Success get all foods by userId {}", userId);

        return new ResponseApi<>(result);
    }

    /**
     * Get List of Food by text
     *
     * @param userId User ID
     * @param text   text
     * @return List of {@link FavoritesFoodDto}
     */
    @GetMapping("/text")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<List<FavoritesFoodDto>> getFavoritesFoodByString(@RequestHeader UUID userId,
                                                                        @RequestParam String text
    ) {
        log.info("#GET: Get all foods by userId {} and by string {}", userId, text);
        List<FavoritesFoodDto> result = foodService.getListOfFoodByString(userId, text);
        log.info("#GET: Success get all foods by userId {} and by string {}", userId, text);

        return new ResponseApi<>(result);
    }

    /**
     * Save Favorites Food
     *
     * @param userId  User ID
     * @param request {@link SaveFoodRequest}
     * @return {@link FavoritesFoodDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<FavoritesFoodDto> saveFavoritesFood(@RequestHeader UUID userId,
                                                           @RequestBody SaveFoodRequest request
    ) {
        log.info("#POST: Save food by userId {} and by beerId {}", userId, request.getForeignBeerApiId());
        FavoritesFoodDto favoritesFoodDto = RequestFoodConverter.INSTANCE.getFoodDtoFromRequest(userId, request);
        FavoritesFoodDto result = foodService.saveOneFavoritesFood(favoritesFoodDto);
        log.info("#POST: Success save food by userId {} and by beerId {}", userId, request.getForeignBeerApiId());

        return new ResponseApi<>(result);
    }

    /**
     * Delete One Favorites Food
     *
     * @param id ID of Favorites Food
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

        return new ResponseApi<>(true);
    }
}