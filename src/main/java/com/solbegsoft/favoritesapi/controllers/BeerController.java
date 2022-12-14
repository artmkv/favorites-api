package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.RequestBeerConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import java.util.Optional;
import java.util.UUID;

/**
 * Favorites beer controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("favorites-api/v1/beers")
@RequiredArgsConstructor
public class BeerController {

    /**
     * @see BeerService
     */
    private final BeerService beerService;

    /**
     * Get All favorites beers by userId
     *
     * @param page   page for pagination
     * @param size   size of page for pagination
     * @param order  sorting by
     * @param rate   rating beers
     * @param userId User ID
     * @return {@link ResponseApi}
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<Page<FavoritesBeerDto>> getBeerFavorites(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(defaultValue = "20", required = false) Integer size,
                                                                @RequestParam(defaultValue = "id", required = false) String order,
                                                                @RequestParam(required = false) Integer[] rate,
                                                                @RequestHeader(value = "userId") UUID userId
    ) {
        log.info("#GET: Get all beers by userId {}, rate {}", userId, rate);

        GetBeerRequestDto requestDto = RequestBeerConverter.convertToGetRequestDto(
                userId,
                rate,
                PageRequest.of(page, size, Sort.by(order)));
        Page<FavoritesBeerDto> favorites = beerService.getAllBeersByRate(requestDto);

        log.info("#GET: Success get all beers by userId {}, rate {}", userId, rate);

        return new ResponseApi<>(favorites);
    }

    /**
     * Get Favorites Beer by beer ID and by User ID
     *
     * @param id     Favorites beer ID
     * @param userId user ID
     * @return {@link Optional} of Favorites beers
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<FavoritesBeerDto> getBeerFavoritesById(@PathVariable("id") UUID id,
                                                              @RequestHeader UUID userId
    ) {
        log.info("#GET: Get beer by userId {}, ID {}", userId, id);
        FavoritesBeerDto favorites = beerService.getBeerById(userId, id);
        log.info("#GET: Success get beer by userId {}, ID {}", userId, id);

        return new ResponseApi<>(favorites);
    }

    /**
     * Save favorite beer
     *
     * @param request Save request favorites beers
     * @param userId  user ID
     * @return {@link FavoritesBeerDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<FavoritesBeerDto> saveBeerFavorites(@RequestBody @Valid SaveFavoritesBeerRequest request,
                                                           @RequestHeader UUID userId
    ) {
        log.info("#POST: userId {}, beerID {}", userId, request.getForeignBeerApiId());

        SaveBeerRequestDto saveRequestDto = RequestBeerConverter.convertToSaveRequestDto(userId, request);
        FavoritesBeerDto result = beerService.saveFavoriteBeer(saveRequestDto);

        log.info("#POST: Save success with userId {}, beerID {}", userId, request.getForeignBeerApiId());

        return new ResponseApi<>(result);
    }

    /**
     * Update favorite beer
     *
     * @param id      Favorites beer ID
     * @param userId  user ID
     * @param request favorite beer request
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<FavoritesBeerDto> updateBeerFavorites(@PathVariable("id") UUID id,
                                                             @RequestHeader UUID userId,
                                                             @RequestBody @Valid UpdateFavoritesBeerRequest request
    ) {

        log.info("#PATCH: userId {}, beerID {}", userId, id);

        UpdateBeerRequestDto updateBeerRequestDto = RequestBeerConverter.convertToUpdateRequestDto(userId, id, request);
        FavoritesBeerDto result = beerService.updateFavoriteBeer(updateBeerRequestDto);

        log.info("#PATCH: updated success userId {}, beerID {}", userId, id);

        return new ResponseApi<>(result);
    }

    /**
     * Update one favorite beer
     *
     * @param id     Favorites beer ID
     * @param rate   rating of favorites beer
     * @param userId user ID
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}/rate/{rate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<FavoritesBeerDto> updateRateOfBeerFavorites(@PathVariable("id") UUID id,
                                                                   @PathVariable("rate") @DecimalMax(value = "5") Integer rate,
                                                                   @RequestHeader UUID userId
    ) {
        log.info("#PATCH: userId {}, beerUUID {}", userId, id);

        UpdateBeerRequestDto updateBeerRequestDto = RequestBeerConverter.convertToUpdateRequestDto(userId, id, rate);
        FavoritesBeerDto favoritesBeer = beerService.updateRateFavoritesBeer(updateBeerRequestDto);

        log.info("#PATCH: updated success userId {}, beerUUID {}", userId, id);

        return new ResponseApi<>(favoritesBeer);
    }

    /**
     * Delete one favorites beer
     *
     * @param id     Favorites beer ID
     * @param userId User ID
     * @return boolean
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<Boolean> deleteBeerFavoritesById(@PathVariable("id") UUID id,
                                                        @RequestHeader UUID userId
    ) {
        log.info("#DELETE: userId {}, Id {}", userId, id);
        beerService.deleteFavoriteBeer(userId, id);
        log.info("#DELETE: deleted success userId {}, Id {}", userId, id);

        return new ResponseApi<>(Boolean.TRUE);
    }
}