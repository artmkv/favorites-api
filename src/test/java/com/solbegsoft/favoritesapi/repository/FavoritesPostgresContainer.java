package com.solbegsoft.favoritesapi.repository;


import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Container for testing Favorites Repository
 */
public class FavoritesPostgresContainer extends PostgreSQLContainer<FavoritesPostgresContainer> {

    /**
     * Image version
     */
    private static final String IMAGE_VERSION = "postgres:14.1";

    /**
     * Container
     */
    private static FavoritesPostgresContainer container;

    /**
     * Constructor
     */
    private FavoritesPostgresContainer() {
        super(IMAGE_VERSION);
    }

    /**
     * Get instance
     *
     * @return {@link FavoritesPostgresContainer}
     */
    public static FavoritesPostgresContainer getInstance() {
        if (container == null) {
            container = new FavoritesPostgresContainer()
                    .withDatabaseName("my-test-db")
                    .withUsername("sa")
                    .withPassword("pass");
        }
        return container;
    }
}