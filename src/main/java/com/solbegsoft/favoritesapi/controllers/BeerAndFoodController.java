package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.models.response.ResponseBeerWithFood;
import com.solbegsoft.favoritesapi.services.BeerAndFoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Favorites beer controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("favorites-api/v1/beers")
@RequiredArgsConstructor
public class BeerAndFoodController {

    /**
     * @see BeerAndFoodService
     */
    private final BeerAndFoodService beerAndFoodService;

    /**
     * Get Favorites Beer with Favorites Food
     *
     * @param id     Favorites Beer ID
     * @param userId User Id
     * @return {@link ResponseApi}
     */
    @GetMapping("/{id}/foods")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<ResponseBeerWithFood> getBeerFavoritesById(@PathVariable("id") UUID id,
                                                                  @RequestHeader UUID userId
    ) {
        log.info("#GET: Get beer with food by userId {}, ID of beer {}", userId, id);
        ResponseBeerWithFood result = beerAndFoodService.getBeerByIdWithFood(userId, id);
        log.info("#GET: Success get beer with food by userId {}, ID of beer  {}", userId, id);

        return new ResponseApi<>(result);
    }
}