package com.solbegsoft.favoritesapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Favorites Api Application
 */
@EnableEurekaClient
@EnableJpaRepositories
@SpringBootApplication
public class FavoritesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoritesApiApplication.class, args);
    }

}