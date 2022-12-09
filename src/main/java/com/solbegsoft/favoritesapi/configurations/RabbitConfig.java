package com.solbegsoft.favoritesapi.configurations;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit Configuration
 */
@Configuration
public class RabbitConfig {

    /**
     * Queue to Favorites API
     */
    @Value("${spring.application.name}")
    static final String MICROSERVICE_FAVORITES_NAME = "favorites-api";

    /**
     * Queue to Beers API
     */
    @Value("${rabbit.queues.beers-api}")
    static final String MICROSERVICE_BEERS_NAME = "beers-api";

    /**
     * Queue to Auth API
     */
    @Value("${rabbit.queues.auth-api}")
    static final String MICROSERVICE_AUTH_NAME = "auth-api";

    /**
     * Queue for ERROR messages
     */
    @Value("${rabbit.queues.error}")
    public static final String QUEUE_NAME_ERROR = "error";

    @Bean
    public Queue queueFavoritesOutput() {

        return new Queue("favorites-api.queue.get-all.output");
    }

    @Bean
    public Queue queueFavoritesInput() {

        return new Queue("favorites-api.queue.get-all.input");
    }

    @Bean
    public Queue queueBeersOutput() {

        return new Queue("beers-api.queue.get-all.output");
    }

    @Bean
    public Queue queueBeersInput() {

        return new Queue("beers-api.queue.get-all.input");
    }

    @Bean
    public Queue queueFavoritesError() {

        return new Queue("favorites-api.queue.get-all.error");
    }
}