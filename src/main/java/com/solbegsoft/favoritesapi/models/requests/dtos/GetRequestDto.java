package com.solbegsoft.favoritesapi.models.requests.dtos;


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
public class GetRequestDto extends BaseRequestDto {

    /**
     * FavoritesBeer ID
     */
    private UUID id;

    /**
     * Rate array
     */
    private Set<Integer> rate;

    /**
     * @see Pageable
     */
    private Pageable pageable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GetRequestDto that = (GetRequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(rate, that.rate) && Objects.equals(pageable, that.pageable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, rate, pageable);
    }
}
