package com.solbegsoft.favoritesapi.rabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.solbegsoft.favoritesapi.rabbit.AsyncConfig.QUEUE_NAME_FAVORITES;

/**
 * Async Listener
 */
@Slf4j
@RabbitListener(queues = QUEUE_NAME_FAVORITES)
@Component
public class AsyncReceiver {

    /**
     * Async Rabbit Handler
     *
     * @param message string
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        // TODO: 24.11.2022  бить по рукам за "+" в логах!!!!! в другом классе описано как нужно сделать =)
        log.info("Received  <" + message + ">");
    }

}
