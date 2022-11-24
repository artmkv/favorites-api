package com.solbegsoft.favoritesapi.rabbit;


import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.rabbit.AsyncService;
import com.solbegsoft.favoritesapi.rabbit.request.AsyncBeerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// TODO: 24.11.2022 java doc ?
// favorites-api/v1/async - исходя из этого EP я только понимаю, что будет асинхронщина, а куда и какой вообще запрос - непонятно
@Slf4j
@RestController
@RequestMapping("favorites-api/v1/async")
@RequiredArgsConstructor
public class AsyncRabbitController {

    // TODO: 24.11.2022 так он  AsyncService или  rabbitService ?
	private final AsyncService rabbitService;


	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	// TODO: 24.11.2022 почему getSendMessageToRabbitBroker может просто sendMessageToRabbitBroker ну и какой месседж? а если появится еще 1 EP ?
	public ResponseApi<String> getSendMessageToRabbitBroker(@RequestParam(required = false) String beerName,
															@RequestParam(required = false) String foodName
	) {
        // TODO: 24.11.2022 Ну каммон, ну какие конкатенации в логах, тут есть механизм подстановки. Поправь везде
//		log.info("#GET: Send RABBIT MESSAGE: <{}{}>", beerName, foodName);
		log.info("#GET: Send RABBIT MESSAGE: <" + beerName + foodName + ">");

		AsyncBeerRequestDto request = AsyncBeerRequestDto.builder()
				.beerName(beerName)
				.foodName(foodName)
				.build();

		log.info("#GET: Send RABBIT MESSAGE: <" + request.toString() + ">");
		rabbitService.send(request);

		log.info("#GET: Send SUCCESSFUL RABBIT MESSAGE: < " + " >");

        // TODO: 24.11.2022 хм.... а зачем?  тут достаточно отдать просто ОК
		return new ResponseApi<>("<" + beerName + foodName + ">");
	}

}
