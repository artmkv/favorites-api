package com.solbegsoft.favoritesapi.rabbit;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.rabbit.AsyncService;
import com.solbegsoft.favoritesapi.rabbit.request.AsyncBeerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("favorites-api/v1/async")
@RequiredArgsConstructor
public class AsyncRabbitController {

    private final AsyncService rabbitService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<String> getSendMessageToRabbitBroker(@RequestParam(required = false) String beerName,
                                                            @RequestParam(required = false) String foodName
    ) {
        log.info("#GET: Send RABBIT MESSAGE: <" + beerName + foodName + ">");

        AsyncBeerRequestDto request = AsyncBeerRequestDto.builder()
                .beerName(beerName)
                .foodName(foodName)
                .build();

        log.info("#GET: Send RABBIT MESSAGE: <" + request.toString() + ">");
        rabbitService.send(request);

        log.info("#GET: Send SUCCESSFUL RABBIT MESSAGE: < " + " >");

        return new ResponseApi<>("<" + beerName + foodName + ">");
    }

}
