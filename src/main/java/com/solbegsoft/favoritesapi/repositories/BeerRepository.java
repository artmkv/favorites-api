package com.solbegsoft.favoritesapi.repositories;


import com.solbegsoft.favoritesapi.models.dtos.RequestDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Beer Repository
 */
@NoRepositoryBean
public interface BeerRepository extends JpaRepository<FavoritesBeer, Long> {

    /**
     * Find All {@link FavoritesBeer}
     *
     * @param requestDto request DTO
     * @return {@link Page}
     */
    @Query(value = "select b from FavoritesBeer b " +
            "where b.userId = #{@requestDto.userId} " +
            "order by #{@requestDto.pageable.getSort()}")
    Page<FavoritesBeer> findAll(RequestDto requestDto);

    /**
     * Find All {@link FavoritesBeer} by Rate
     *
     * @param requestDto request DTO
     * @return {@link Page}
     */
    @Query("select b from FavoritesBeer b where (b.userId = #{@requestDto.userId} and b.rate in (:#{@requestDto.rate})) group by b.rate")
    Page<FavoritesBeer> findAllByRate(RequestDto requestDto);

    /**
     * Update {@link FavoritesBeer}
     *
     * @param requestDto request DTO
     * @return {@link FavoritesBeer}
     */
    @Modifying
    @Query("update FavoritesBeer b set b.rate = #{@requestDto.requestFavoritesBeer.rate} " +
            "where b.id = #{@requestDto.requestFavoritesBeer.id}")
    FavoritesBeer updateFavoriteBeerById(RequestDto requestDto);

    /**
     * Save {@link FavoritesBeer}
     *
     * @param requestDto request DTO
     * @return {@link FavoritesBeer}
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "insert into FavoritesBeer(userId, beerId, rate) " +
                    "values(#{@requestDto.userId}, " +
                    "#{@requestDto.requestFavoritesBeer.beerId}," +
                    "#{@requestDto.requestFavoritesBeer.rate})")
    FavoritesBeer saveOneFavoriteBeer(RequestDto requestDto);

    /**
     * Delete {@link FavoritesBeer}
     *
     * @param userId user ID
     * @param id     ID of FavoritesBeer
     */
    @Modifying
    @Query("delete from FavoritesBeer b where b.userId = ?1 and b.id = ?2")
    void deleteOne(Long userId, UUID id);
}
