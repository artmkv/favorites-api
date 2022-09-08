package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Beer Repository
 */
@Repository
@Transactional
public interface BeerRepository extends JpaRepository<FavoritesBeer, UUID> {

    @Query("select b from FavoritesBeer b where b.userId = :#{#dto.userId}")
    Page<FavoritesBeer> getAllWithPagination(@Param("dto") GetRequestDto dto, Pageable pageable);

    @Query("select b from FavoritesBeer b where b.userId = :#{#dto.userId} and b.rate in :#{#dto.rate}")
    Page<FavoritesBeer> getAllBySetRatesWithPagination(@Param("dto") GetRequestDto dto, Pageable pageable);

    @Query("select b from FavoritesBeer b where b.userId = ?1 and b.id = ?2")
    Optional<FavoritesBeer> findOneBeerById(UUID userId, UUID id);

    @Modifying
    @Query("delete from FavoritesBeer b where b.userId = ?1 and b.id = ?2")
    void deleteOne(UUID userId, UUID uuid);

    @Modifying
    @Query("update FavoritesBeer b set b.rate = :#{#dto.rate} " +
            "where b.id = :#{#dto.id} and b.userId = :#{#dto.userId}")
    void updateRateFavoritesBeer(@Param("dto") UpdateRequestDto dto);

    @Modifying
    @Query("update FavoritesBeer b set b = :#{#entity} where b.id = :#{#entity.id} and b.userId = :#{#entity.userId}")
    void updateFavoritesBeer(@Param("entity") FavoritesBeer entity);

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into favorites.favorites_beer(user_id, beer_api_id, rate, name, abv, ibu, ebc) " +
                    "values(:#{#dto.userId}, :#{#dto.foreignBeerApiId}, :#{#dto.rate}, :#{#dto.name}, :#{#dto.abv}, :#{#dto.ibu}, :#{#dto.ebc})")
    void saveFavoritesBeer(@Param("dto") SaveRequestDto dto);

    @Query("select b from FavoritesBeer b where b.userId = :#{#userId} and b.foreignBeerApiId = :#{#foreignBeerApiId}")
    Optional<FavoritesBeer> findByUserAndBeer(UUID userId, Long foreignBeerApiId);

}
