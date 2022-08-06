package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.RequestDto;
import com.solbegsoft.favoritesapi.models.requests.RequestFavoritesBeer;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.BeerService;
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
        Page<FavoritesBeerDto> favorites = beerService.getAllBeersByRate(RequestDto.builder()
                .userId(userId)
                .pageable(PageRequest.of(page, size, Sort.by(order)))
                .rate(Collections.asSet(rate))
                .build()
        );
        log.info("#GET: Success get all beers by userId {}, rate {}", userId, rate);

        return new ResponseApi<>(favorites);
    }

    /**
     * Get Favorites Beer by beer ID and by User ID
     *
     * @param uuid   ID of beer
     * @param userId ID of user
     * @return {@link Optional} of favorites beers
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<FavoritesBeerDto> getBeerFavoritesById(@PathVariable("id") UUID uuid,
                                                              @RequestHeader Long userId
    ) {
        log.info("#GET: Get beer by userId {}, beerID {}", userId, uuid);
        FavoritesBeerDto favorites = beerService.getBeerById(userId, uuid);
        log.info("#GET: Success get beer by userId {}, beerID {}", userId, uuid);

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
    public ResponseApi<FavoritesBeerDto> saveBeerFavorites(@RequestBody @Valid RequestFavoritesBeer request,
                                                           @RequestHeader Long userId
    ) {
        FavoritesBeerDto result = beerService.saveFavoriteBeer(RequestDto.builder()
                .userId(userId)
                .requestFavoritesBeer(request)
                .build());
        return new ResponseApi<>(result);
    }

    /**
     * Update favorite beer
     *
     * @param uuid    beer ID
     * @param userId  user ID
     * @param request favorite beer request
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<FavoritesBeerDto> updateBeerFavorites(@PathVariable("id") UUID uuid,
                                                             @RequestHeader Long userId,
                                                             @RequestBody @Valid RequestFavoritesBeer request
    ) {
        log.info("#PATCH: userId {}, beerID {}", userId, uuid);
        FavoritesBeerDto result = beerService.updateFavoriteBeer(RequestDto.builder()
                .userId(userId)
                .uuid(uuid)
                .requestFavoritesBeer(request)
                .build()
        );
        log.info("#PATCH: updated success userId {}, beerID {}", userId, uuid);

        return new ResponseApi<>(result);
    }

    /**
     * Update one favorite beer
     *
     * @param uuid   beer UUID ID
     * @param rate   rating of favorites beer
     * @param userId user ID
     * @return {@link FavoritesBeerDto}
     */
    @PatchMapping("/{id}/{rate}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<FavoritesBeerDto> updateRateOfBeerFavorites(@PathVariable("id") UUID uuid,
                                                                   @PathVariable("rate") Integer rate,
                                                                   @RequestHeader Long userId
    ) {
        log.info("#PATCH: userId {}, beerID {}", userId, uuid);
        FavoritesBeerDto result = beerService.updateFavoriteBeer(RequestDto.builder()
                .userId(userId)
                .uuid(uuid)
                .rate(Collections.asSet(rate))
                .requestFavoritesBeer(RequestFavoritesBeer.builder()
                        .uuid(uuid)
                        .rate(rate)
                        .build())
                .build()
        );
        log.info("#PATCH: updated success userId {}, beerID {}", userId, uuid);

        return new ResponseApi<>(result);
    }

    /**
     * Delete one favorites beer
     *
     * @param uuid   beer ID
     * @param userId user ID
     * @return boolean
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<Boolean> deleteBeerFavoritesById(@PathVariable("id") UUID uuid,
                                                        @RequestHeader Long userId
    ) {
        log.info("#DELETE: userId {}, beerID {}", userId, uuid);
        beerService.deleteFavoriteBeer(userId, uuid);
        log.info("#DELETE: deleted success userId {}, beerID {}", userId, uuid);

        return new ResponseApi<>(Boolean.TRUE);
    }
}