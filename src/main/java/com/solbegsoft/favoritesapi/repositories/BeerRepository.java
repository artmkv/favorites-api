package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
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

    /**
     * Find all favorites beer with pagination
     *
     * @param dto      {@link GetBeerRequestDto}
     * @param pageable {@link Pageable}
     * @return {@link Page} of {@link FavoritesBeer}
     */
    @Query("select b from FavoritesBeer b where b.userId = :#{#dto.userId}")
    Page<FavoritesBeer> findAllWithPagination(@Param("dto") GetBeerRequestDto dto, Pageable pageable);

    /**
     * Find all favorites beer by rate with pagination
     *
     * @param dto      {@link GetBeerRequestDto}
     * @param pageable {@link Pageable}
     * @return {@link Page} of {@link FavoritesBeer}
     */
    @Query("select b from FavoritesBeer b where b.userId = :#{#dto.userId} and b.rate in :#{#dto.rate}")
    Page<FavoritesBeer> findAllBySetRatesWithPagination(@Param("dto") GetBeerRequestDto dto, Pageable pageable);

    /**
     * Find favorites beer
     *
     * @param userId User ID
     * @param beerId Beer ID
     * @return {@link Optional} of {@link FavoritesBeer}
     */
    @Query("select b from FavoritesBeer b where b.userId = :#{#userId} and b.id = :#{#beerId}")
    Optional<FavoritesBeer> findOneBeerById(@Param("userId") UUID userId,
                                            @Param("beerId") UUID beerId);

    /**
     * Delete one Favorites beer
     *
     * @param userId User ID
     * @param beerId Beer ID
     */
    @Modifying
    @Query("delete from FavoritesBeer b where b.userId = :#{#userId} and b.id = :#{#beerId}")
    void deleteOne(@Param("userId") UUID userId,
                   @Param("beerId") UUID beerId);

    /**
     * Update Rate for Favorites Beer
     *
     * @param dto {@link UpdateBeerRequestDto}
     */
    @Modifying
    @Query("update FavoritesBeer b set b.rate = :#{#dto.rate} " +
            "where b.id = :#{#dto.id} and b.userId = :#{#dto.userId}")
    void updateRateFavoritesBeer(@Param("dto") UpdateBeerRequestDto dto);

    /**
     * Update Favorites Beer
     *
     * @param entity {@link FavoritesBeer}
     */
    @Modifying
    @Query("update FavoritesBeer b set b = :#{#entity} where b.id = :#{#entity.id} and b.userId = :#{#entity.userId}")
    void updateFavoritesBeer(@Param("entity") FavoritesBeer entity);

    /**
     * Save Favorites beer
     *
     * @param entity {@link FavoritesBeer}
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into favorites.favorites_beer(user_id, beer_api_id, rate, name, abv, ibu, ebc) " +
                    "values(:#{#entity.userId}, :#{#entity.foreignBeerApiId}, :#{#entity.rate}, :#{#entity.name}, :#{#entity.abv}, :#{#entity.ibu}, :#{#entity.ebc})")
    void saveFavoritesBeer(@Param("entity") FavoritesBeer entity);

    /**
     * Find Favorites beer by User and Beer
     *
     * @param userId           User ID
     * @param foreignBeerApiId Foreign Beer ID
     * @return @return {@link Optional} of {@link FavoritesBeer}
     */
    @Query("select b from FavoritesBeer b where b.userId = :#{#userId} and b.foreignBeerApiId = :#{#foreignBeerApiId}")
    Optional<FavoritesBeer> findByUserAndBeer(@Param("userId") UUID userId,
                                              @Param("foreignBeerApiId") Long foreignBeerApiId);

}