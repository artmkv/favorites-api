package com.solbegsoft.favoritesapi.rabbit;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.rabbit.request.RabbitBeerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint which send request to Beer API using RabbitMQ
 */
@Slf4j
@RestController
@RequestMapping("favorites-api/v1/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    /**
     * @see RabbitSender
     */
    private final RabbitSender rabbitSender;

    /**
     * Method Get All Beers using RabbitMQ
     *
     * @param beerName name of beer
     * @param foodName food name
     * @return {@link ResponseApi}
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseApi<String> sendToRabbitBroker(@RequestParam(required = false) String beerName,
                                                  @RequestParam(required = false) String foodName
    ) {
        log.info("#GET: Send RABBIT MESSAGE: <{}{}>", beerName, foodName);

        RabbitBeerRequestDto request = RabbitBeerRequestDto.builder()
                .beerName(beerName)
                .foodName(foodName)
                .build();

        log.info("#GET: Send RABBIT MESSAGE: <{}>", request.toString());
        rabbitSender.send(request);
        log.info("#GET: Send MESSAGE to RABBIT SUCCESSFUL");
        return new ResponseApi<>("");
    }
}