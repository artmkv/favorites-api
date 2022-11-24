package com.solbegsoft.favoritesapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.rabbit.request.AsyncBeerRequestDto;
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
public class AsyncService {
    // TODO: 24.11.2022 тоже имя не оч, RabbitSender или что-то близкое по духу

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
    private final Queue queueBeers;

    /**
     * Send message to Favorites
     *
     * @param request request with parameters
     */
    public void send(AsyncBeerRequestDto request) {
        String sendString;
        try {
            sendString = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new AsyncException(e.getMessage());
        }
        this.template.convertAndSend(queueBeers.getName(), sendString);
        log.info(" [X] Sent '" + request + "' routing" + queueBeers.getName());
    }

    // TODO: 24.11.2022 имхо так лаконичнее будет
    /**
     *         try {
     *             String sendString = objectMapper.writeValueAsString(request);
     *             this.template.convertAndSend(queueBeers.getName(), sendString);
     *             log.info(" [X] Sent '" + request + "' routing" + queueBeers.getName());
     *         } catch (JsonProcessingException e) {
     *             throw new AsyncException(e.getMessage());
     *         }
     */
}