package com.solbegsoft.favoritesapi.rabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Rabbit Listener
 */
@Slf4j
@Component
public class RabbitReceiver {

    /**
     * Rabbit handler queue Favorites input
     *
     * @param message string
     */
    @RabbitListener(queues = "favorites-api.queue.get-all.input")
    @RabbitHandler
    public void receiveMessageFavoritesInput(String message) {
        log.info("Received |favorites-api.queue.get-all.input| <{}>", message);
    }

    /**
     * Rabbit handler queue Beers output
     *
     * @param message string
     */
    @RabbitListener(queues = "beers-api.queue.get-all.output")
    @RabbitHandler
    public void receiveMessageBeersOutput(String message) {
        log.info("Received |beers-api.queue.get-all.output| <{}>", message);
    }
}