package com.solbegsoft.favoritesapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.rabbit.request.RabbitBeerRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Rabbit service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitSender {

    /**
     * @see RabbitTemplate
     */
    private final RabbitTemplate template;

    /**
     * @see ObjectMapper
     */
    private final ObjectMapper objectMapper;

    /**
     * @see Queue
     */
    private final Queue queueFavoritesOutput;

    /**
     * @see Queue
     */
    private final Queue queueBeersInput;

    /**
     * Send message to Favorites
     *
     * @param request request with parameters
     */
    public void send(RabbitBeerRequestDto request) {
        try {
            String sendString = objectMapper.writeValueAsString(request);
            this.template.convertAndSend(queueFavoritesOutput.getName(), sendString);
            this.template.convertAndSend(queueBeersInput.getName(), sendString);
            log.info(" [X] Sent Rabbit '{}' routing {}", request, queueFavoritesOutput.getName());
        } catch (JsonProcessingException e) {
            throw new RabbitException(e.getMessage());
        }
    }
}