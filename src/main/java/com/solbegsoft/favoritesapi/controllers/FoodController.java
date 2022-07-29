package com.solbegsoft.favoritesapi.controllers;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Favorites food controller
 */
@RestController
@RequestMapping("favorites-api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<String> getFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<String> saveFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> updateFoodFavorites(){

        return new ResponseApi<>("message");
    }

    @DeleteMapping ("/del")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseApi<String> deleteFoodFavorites(){

        return new ResponseApi<>("message");
    }
}
