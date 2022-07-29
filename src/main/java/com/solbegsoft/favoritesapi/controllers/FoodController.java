package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Favorites food controller
 */
@RestController
@RequestMapping("favorites-api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<String> getFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<String> saveFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> updateFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> deleteFoodFavorites(@PathVariable("id") UUID foodId,
                                                   @RequestHeader Integer userId
    ){

        return new ResponseApi<>("message");
    }
}
