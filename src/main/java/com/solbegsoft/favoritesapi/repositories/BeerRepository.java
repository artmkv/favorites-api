package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.dtos.RequestDto;
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
    Page<FavoritesBeer> getAllWithPagination(@Param("dto") RequestDto dto, Pageable pageable);

    @Query("select b from FavoritesBeer b where b.userId = :#{#dto.userId} and b.rate in :#{#dto.rate}")
    Page<FavoritesBeer> getAllBySetRatesWithPagination(@Param("dto") RequestDto dto, Pageable pageable);

    @Query("select b from FavoritesBeer b where b.userId = ?1 and b.uuid = ?2")
    FavoritesBeer getOneBeerById(Long userId, UUID uuid);

    @Modifying
    @Query("delete from FavoritesBeer b where b.userId = ?1 and b.uuid = ?2")
    void deleteOne(Long userId, UUID uuid);

    @Modifying
    @Query("update FavoritesBeer b set b.rate = :#{#dto.requestFavoritesBeer.rate} where b.uuid = :#{#dto.requestFavoritesBeer.uuid}")
    void updateRateFavoriteBeer(@Param("dto") FavoritesBeer entity);


    @Modifying
    @Query("update FavoritesBeer b set b = :#{#dto} where b.uuid = :#{#dto.requestFavoritesBeer.uuid}")
    void updateFavoriteBeer(@Param("dto") FavoritesBeer entity);

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into favorites(user_id, beer_api_id, rate) " +
                    "values(:#{#dto.userId}, :#{#dto.requestFavoritesBeer.beerId}, :#{#dto.requestFavoritesBeer.rate})")
    int saveFavoritesBeer(@Param("dto") RequestDto dto);

    Optional<FavoritesBeer> findByUserIdAndBeerId(Long userId, Long beerId);


}
