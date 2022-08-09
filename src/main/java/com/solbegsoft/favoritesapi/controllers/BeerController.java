package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.RequestFavoritesBeerToRequestDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Collections;
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
     * Converter
     */
    private final RequestFavoritesBeerToRequestDtoConverter converter;

    /**
     * Get All favorites beers by userId
     *
     * @param page   page for pagination
     * @param size   size of page for pagination
     * @param order  sorting by
     * @param rate   rating beers
     * @param userId user ID
     * @return {@link ResponseApi with List Favorites Beer}
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<Page<FavoritesBeerDto>> getBeerFavorites(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                                @RequestParam(defaultValue = "20", required = false) Integer size,
                                                                @RequestParam(defaultValue = "id", required = false) String order,
                                                                @RequestParam(required = false) Integer[] rate,
                                                                @RequestHeader(value = "userId") Long userId
    ) {
        log.info("#GET: Get all beers by userId {}, rate {}", userId, rate);
        Page<FavoritesBeerDto> favorites = beerService.getAllBeersByRate(
                converter.getInstance().convertToGetRequestDto(
                        userId,
                        Collections.asSet(rate),
                        PageRequest.of(page, size, Sort.by(order)))
        );
        log.info("#GET: Success get all beers by userId {}, rate {}", userId, rate);

        return new ResponseApi<>(favorites);
    }

    /**
     * Get Favorites Beer by beer ID and by User ID
     *
     * @param id     ID of beer
     * @param userId ID of user
     * @return {@link Optional} of favorites beers
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<FavoritesBeerDto> getBeerFavoritesById(@PathVariable("id") UUID id,
                                                              @RequestHeader Long userId
    ) {
        log.info("#GET: Get beer by userId {}, ID {}", userId, id);
        FavoritesBeerDto favorites = beerService.getBeerById(userId, id);
        log.info("#GET: Success get beer by userId {}, ID {}", userId, id);

        return new ResponseApi<>(favorites);
    }

    /**
     * Save favorite beer
     *
     * @param request request favorites beers
     * @param userId  ID of user
     * @return {@link FavoritesBeerDto}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<FavoritesBeerDto> saveBeerFavorites(@RequestBody @Valid SaveFavoritesBeerRequest request,
                                                           @RequestHeader Long userId
    ) {
        log.info("#POST: userId {}, beerID {}", userId, request.getBeerId());
        FavoritesBeerDto result = beerService.saveFavoriteBeer(converter.convertToSaveRequestDto(userId, request));
        log.info("#POST: Save success with userId {}, beerID {}", userId, request.getBeerId());

        return new ResponseApi<>(result);
    }

    /**
     * Update favorite beer
     *
     * @param id      beer ID
     * @param userId  user ID
     * @param request favorite beer request
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<FavoritesBeerDto> updateBeerFavorites(@PathVariable("id") UUID id,
                                                             @RequestHeader Long userId,
                                                             @RequestBody @Valid UpdateFavoritesBeerRequest request
    ) {
        log.info("#PATCH: userId {}, beerID {}", userId, id);
        FavoritesBeerDto result = beerService.updateFavoriteBeer(
                converter.getInstance().convertToUpdateRequestDto(userId, id, request)
        );
        log.info("#PATCH: updated success userId {}, beerID {}", userId, id);

        return new ResponseApi<>(result);
    }

    /**
     * Update one favorite beer
     *
     * @param id     beer UUID ID
     * @param rate   rating of favorites beer
     * @param userId user ID
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}/{rate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<Boolean> updateRateOfBeerFavorites(@PathVariable("id") UUID id,
                                                                   @PathVariable("rate") @DecimalMax(value = "5") Integer rate,
                                                                   @RequestHeader Long userId
    ) {
        log.info("#PATCH: userId {}, beerUUID {}", userId, id);

        beerService.updateRateFavoritesBeer(
                converter.getInstance().convertToUpdateRequestDto(userId, id, rate));

        log.info("#PATCH: updated success userId {}, beerUUID {}", userId, id);

        return new ResponseApi<>(Boolean.TRUE);
    }

    /**
     * Delete one favorites beer
     *
     * @param id     beerUUID
     * @param userId user ID
     * @return boolean
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<Boolean> deleteBeerFavoritesById(@PathVariable("id") UUID id,
                                                        @RequestHeader Long userId
    ) {
        log.info("#DELETE: userId {}, id {}", userId, id);
        beerService.deleteFavoriteBeer(userId, id);
        log.info("#DELETE: deleted success userId {}, Id {}", userId, id);

        return new ResponseApi<>(Boolean.TRUE);
    }
}