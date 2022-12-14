package com.solbegsoft.favoritesapi.rabbit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.controllers.AbstractMVCTest;
import com.solbegsoft.favoritesapi.rabbit.request.RabbitBeerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test Rabbit Controller Test
 */
class RabbitControllerTest extends AbstractMVCTest {

    /**
     * General Endpoint
     */
    private static final String ENDPOINT = "/favorites-api/v1/rabbit";

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
     * Test method {@link RabbitController#sendToRabbitBroker(String, String)}
     *
     * @throws Exception exception
     */
    @Test
    void testSendToRabbitBroker_WithRequest_ShouldReturnStatusOkAndEmptyData() throws Exception {
        RabbitBeerRequestDto request = createRabbitBeerRequestDto();
        String sendString = objectMapper.writeValueAsString(request);

        mockMvc.perform(get(ENDPOINT)
                        .param("beerName", request.getBeerName())
                        .param("foodName", request.getFoodName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());

        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersInput.getName(), sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueFavoritesOutput.getName(), sendString);
    }

    /**
     * Test method {@link RabbitController#sendToRabbitBroker(String, String)}
     *
     * @throws Exception exception
     */
    @Test
    void testSendToRabbitBroker_WithEmptyRequest_ShouldReturnStatusOkAndEmptyData() throws Exception {
        RabbitBeerRequestDto request = RabbitBeerRequestDto.builder().build();
        String sendString = objectMapper.writeValueAsString(request);

        mockMvc.perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());

        verify(rabbitTemplate, times(1)).convertAndSend(queueBeersInput.getName(), sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueFavoritesOutput.getName(), sendString);
    }

    /**
     * Test method {@link RabbitController#sendToRabbitBroker(String, String)}
     *
     * @throws Exception exception
     */
    @Test
    void testSendToRabbitBroker_WithRequest_ShouldReturnStatusBadRequest() throws Exception {
        RabbitBeerRequestDto request = createRabbitBeerRequestDto();
        String sendString = objectMapper.writeValueAsString(request);

        mockMvc.perform(get(ENDPOINT)
                        .param("beerName", request.getBeerName())
                        .param("foodName", request.getFoodName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());

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
                .build();
    }
}