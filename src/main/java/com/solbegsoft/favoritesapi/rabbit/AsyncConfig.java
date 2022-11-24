package com.solbegsoft.favoritesapi.rabbit;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Async Configuration
 */
@Configuration
public class AsyncConfig {
    // TODO: 24.11.2022  AsyncConfig плохое имя для этого класса, т.к. тут конфиги очередей. RabbitQueueConfig или что-то в этом роде

    /**
     * тут будет пояснительная бригада по очередям.
     *
     * Смотри, на данный момент у нас есть 2 очереди (beers/favorites) для отправки сервисам, вроде неплохо выглядит.
     * НО!
     * Это все же больше похоже на мусорку и не понятно куда и что ты шлешь, сейчас это выглядит логично пока у тебя только один запрос!
     * А когда их станет больше непонятно что и куда ты шлешь, можно в принципе добавить route.key для разделения.
     * Но я предложу следующий формат service_name.queue.method_name - это даст нас разделение на сервис и используемые "методы"
     * Зачем?
     * Да хотя бы для того, чтобы в будущем отслеживать нагрузку на конкретные методы.
     */


    /**
     * Queue to Favorites API
     */
    static final String QUEUE_NAME_FAVORITES = "favorites";

    /**
     * Queue to Beers API
     */
    static final String QUEUE_NAME_BEERS = "beers";

    /**
     * Queue for ERROR messages
     */
    public static final String QUEUE_NAME_ERROR = "error";

    @Bean
    public Queue queueBeers() {
        return new Queue(QUEUE_NAME_BEERS);
    }

    @Bean
    public Queue queueFavorites() {
        return new Queue(QUEUE_NAME_FAVORITES);
    }

    @Bean
    public Queue queueError() {
        return new Queue(QUEUE_NAME_ERROR);
    }
}