package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select f from FavoritesFood f where f.userId = ?1")
    List<FavoritesFood> getAllFavoritesFood(UUID userId);

    @Query(value = "select f from FavoritesFood f where f.userId = ?1 and lower(f.text) like ?2")
    List<FavoritesFood> getAllFavoritesFoodByString(UUID userId, String str);

    @Query(value = "select f from FavoritesFood f where f.userId = ?1 and f.foreignBeerApiId = ?2")
    List<FavoritesFood> getFavoritesFoodByUserIdAndBeerId (UUID userId, Long foreignBeerApiId);

    @Modifying
    @Query("delete from FavoritesFood f where f.userId = ?1 and f.id = ?2")
    void deleteOne(UUID userId, UUID id);
}