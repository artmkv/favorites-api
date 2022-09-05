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
    public ResponseApi<String> getFoodFavorites(){ // TODO: 05.09.2022 тут нужно вернуть все строки хавчика

        return new ResponseApi<>("message");
    }

    // TODO: 05.09.2022 еще 1 эндпоинт для конкретного хавчика, если в строке есть совпадение достать лист строк

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApi<String> saveFoodFavorites(){ // TODO: 05.09.2022 добавляешь юзеру по бир айди строку с хавчиком

        return new ResponseApi<>("message");
    }
}