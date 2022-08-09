package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
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
    Optional<FavoritesBeer> findOneBeerById(Long userId, UUID id);

    @Modifying
    @Query("delete from FavoritesBeer b where b.userId = ?1 and b.id = ?2")
    void deleteOne(Long userId, UUID uuid);

    @Modifying
    @Query("update FavoritesBeer b set b.rate = :#{#dto.requestFavoritesBeer.rate} " +
            "where b.id = :#{#dto.requestFavoritesBeer.id} and b.userId = :#{#dto.userId}")
    void updateRateFavoriteBeer(@Param("dto") UpdateRequestDto dto);

    @Modifying
    @Query("update FavoritesBeer b set b = :#{#entity} where b.id = :#{#entity.id} and b.userId = :#{#entity.userId}")
    void updateFavoriteBeer(@Param("entity") FavoritesBeer entity);

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into favorites(user_id, beer_api_id, rate) " +
                    "values(:#{#dto.userId}, :#{#dto.requestFavoritesBeer.beerId}, :#{#dto.requestFavoritesBeer.rate})")
    UUID saveFavoritesBeer(@Param("dto") SaveRequestDto dto);

    @Query("select b from FavoritesBeer b where b.userId = :#{#userId} and b.beerId = :#{#beerId}")
    Optional<FavoritesBeer> findByUserAndBeer(Long userId, Long beerId);

}
