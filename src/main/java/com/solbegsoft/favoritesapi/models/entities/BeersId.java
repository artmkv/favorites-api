package com.solbegsoft.favoritesapi.models.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Custom ID
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BeersId implements Serializable {

    /**
     * ID
     */
    private UUID uuid;

    /**
     * ID from Beers_API
     */
    private Long beerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeersId beersId = (BeersId) o;
        return Objects.equals(uuid, beersId.uuid) && Objects.equals(beerId, beersId.beerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, beerId);
    }
}
