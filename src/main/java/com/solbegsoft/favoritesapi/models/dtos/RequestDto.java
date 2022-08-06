package com.solbegsoft.favoritesapi.models.dtos;


import com.solbegsoft.favoritesapi.models.requests.RequestFavoritesBeer;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Request Beer Dto model
 */
@Data
@SuperBuilder
public class RequestDto extends BaseRequestDto<Long>{

    /**
     * Favorites beer ID
     */
    private UUID uuid;

    /**
     * Rate array
     */
    private Set<Integer> rate;

    /**
     * @see RequestFavoritesBeer
     */
    private RequestFavoritesBeer requestFavoritesBeer;

    /**
     * @see Pageable
     */
    private Pageable pageable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(rate, that.rate) && Objects.equals(requestFavoritesBeer, that.requestFavoritesBeer) && Objects.equals(pageable, that.pageable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid, rate, requestFavoritesBeer, pageable);
    }
}
