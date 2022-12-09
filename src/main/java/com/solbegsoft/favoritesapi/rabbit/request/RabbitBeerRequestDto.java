package com.solbegsoft.favoritesapi.rabbit.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Async request to Beer-API
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RabbitBeerRequestDto {

    /**
     * Parameter beer name
     */
    private String beerName;
    /**
     * parameter food name
     */
    private String foodName;
    /**
     * abvGT
     */
    private Double abvGt;
    /**
     * abvLt
     */
    private Double abvLt;
    /**
     * ibuGt
     */
    private Double ibuGt;
    /**
     * ibuLt
     */
    private Double ibuLt;
    /**
     * ebcGt
     */
    private Double ebcGt;
    /**
     * ebcLt
     */
    private Double ebcLt;
}