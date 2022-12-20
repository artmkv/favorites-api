package com.solbegsoft.favoritesapi.repository;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

/**
 * Abstract Repository Test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public abstract class AbstractRepositoryTest {

    /**
     * Container
     */
    @Container
    public static PostgreSQLContainer<FavoritesPostgresContainer> postgresSQLContainer = FavoritesPostgresContainer.getInstance();

    /**
     * UUID userId
     */
    protected final UUID userIdUUID = UUID.fromString("d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4");

}