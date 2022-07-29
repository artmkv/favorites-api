package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.dtos.FavoriteBeerDto;
import com.solbegsoft.favoritesapi.models.requests.RequestFavoriteBeer;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Favorites beer controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("favorites-api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    /**
     * @see BeerService
     */
    private final BeerService beerService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<Object> getBeerFavorites(@RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size,
                                                @RequestParam(defaultValue = "id") String order,
                                                @RequestParam Integer userId,
                                                @RequestParam Integer[] rate
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(order));
        List<FavoriteBeerDto> favorites = beerService.getBeerByRate(userId, rate, pageRequest);
        return new ResponseApi<>(favorites);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<String> saveBeerFavorites(@RequestBody @Valid RequestFavoriteBeer request) {

        beerService.saveFavoriteBeer();

        return new ResponseApi<>("message");
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> updateBeerFavorites(@PathVariable UUID id,
                                                   @RequestBody @Valid RequestFavoriteBeer request) {

        beerService.updateFavoriteBeer();

        return new ResponseApi<>("message");
    }

    @DeleteMapping("/del/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> deleteBeerFavorites(@PathVariable UUID id) {

        beerService.deleteFavoriteBeer(id);
        return new ResponseApi<>("message");
    }

}
