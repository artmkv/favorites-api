package com.solbegsoft.favoritesapi.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.rabbit.request.RabbitBeerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test {@link RabbitSender}
 */
@AutoConfigureMockMvc
@SpringBootTest
class RabbitSenderTest {

    /**
     * @see RabbitTemplate
     */
    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * @see ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @see RabbitSender
     */
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * @see Queue FavoritesAPI Output
     */
    @Autowired
    private Queue queueFavoritesOutput;

    /**
     * @see Queue BeersAPI input
     */
    @Autowired
    private Queue queueBeersInput;

    /**
     * Test {@link RabbitSender#send(RabbitBeerRequestDto)}
     */
    @Test
    void testSend_ShouldFinishWithoutException() throws JsonProcessingException {
        RabbitBeerRequestDto request = createRabbitBeerRequestDto();
        String sendString = objectMapper.writeValueAsString(request);

        rabbitSender.send(request);

        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersInput.getName(), sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueFavoritesOutput.getName(), sendString);
    }

    /**
     * Create {@link RabbitBeerRequestDto}
     *
     * @return {@link RabbitBeerRequestDto}
     */
    private RabbitBeerRequestDto createRabbitBeerRequestDto() {
        return RabbitBeerRequestDto.builder()
                .beerName("Beer")
                .foodName("Pizza")
                .abvGt(2.0)
                .abvLt(50.0)
                .ebcGt(1.0)
                .ebcLt(65.2)
                .ibuGt(3.0)
                .ibuLt(74.2)
                .build();
    }
}