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
 * Controller to Beer with Food
 */
@Slf4j
@Validated
@RestController
@RequestMapping("favorites-api/v1/beer-with-food")
@RequiredArgsConstructor
public class BeerAndFoodController {

    /**
     * @see BeerAndFoodService
     */
    private final BeerAndFoodService beerAndFoodService;

    /**
     * Get Favorites Beer with Favorites Food
     *
     * @param beerId Favorites Beer ID
     * @param userId User Id
     * @return {@link ResponseApi}
     */
    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<ResponseBeerWithFood> getFavoritesBeerWithFoodByBeerId(@PathVariable("beerId") UUID beerId,
                                                                              @RequestHeader UUID userId
    ) {
        log.info("#GET: Get beer with food by userId {}, ID of beer {}", userId, beerId);
        ResponseBeerWithFood result = beerAndFoodService.getBeerWithFoodByBeerId(userId, beerId);
        log.info("#GET: Success get beer with food by userId {}, ID of beer  {}", userId, beerId);

        return new ResponseApi<>(result);
    }
}