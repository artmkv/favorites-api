package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesFoodDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesFood;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface FoodRepository {

    @Query(value = "select f from FavoritesFood f where f.userId = ?1")
    List<FavoritesFood> getAllFavoritesFood(UUID userId);
}
