package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Food repository
 */
@Repository
@Transactional
public interface FoodRepository extends JpaRepository<FavoritesFood, UUID> {

    /**
     * Find All Favorites Food
     *
     * @param userId User ID
     * @return List of {@link FavoritesFood}
     */
    @Query(value = "select f from FavoritesFood f where f.userId = :#{#userId}")
    List<FavoritesFood> findAllFavoritesFood(@Param("userId") UUID userId);

    /**
     * Find All Favorites Food by Beer ID
     *
     * @param userId        User ID
     * @param foreignBeerId Foreign Beer ID
     * @return List of {@link FavoritesFood}
     */
    @Query(value = "select f from FavoritesFood f where f.userId = :#{#userId} and f.foreignBeerApiId = :#{#foreignBeerId}")
    List<FavoritesFood> findAllFavoritesFoodByBeerId(@Param("userId") UUID userId,
                                                     @Param("foreignBeerId") Long foreignBeerId);

    /**
     * Find All Favorites Food by String
     *
     * @param userId    User ID
     * @param maybeFood String
     * @return List of {@link FavoritesFood}
     */
    @Query(value = "select f from FavoritesFood f where f.userId = :#{#userId} and lower(f.text) like %:#{#maybeFood.toLowerCase()}%")
    List<FavoritesFood> findAllFavoritesFoodByString(@Param("userId") UUID userId,
                                                     @Param("maybeFood") String maybeFood);

    /**
     * Delete Favorites Food
     *
     * @param userId User ID
     * @param beerId Beer ID
     */
    @Modifying
    @Query("delete from FavoritesFood f where f.userId = :#{#userId} and f.id = :#{#beerId}")
    void deleteOne(@Param("userId") UUID userId,
                   @Param("beerId") UUID beerId);
}