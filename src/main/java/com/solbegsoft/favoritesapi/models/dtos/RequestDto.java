package com.solbegsoft.favoritesapi.models.dtos;


import com.solbegsoft.favoritesapi.models.requests.RequestFavoritesBeer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Request Beer Dto model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private Integer userId;

    private UUID beerId;

    private Integer[] rate;

    private RequestFavoritesBeer requestFavoritesBeer;

    private Pageable pageable;
}
