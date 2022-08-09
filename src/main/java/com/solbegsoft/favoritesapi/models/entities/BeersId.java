package com.solbegsoft.favoritesapi.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Custom ID
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BeersId implements Serializable {

    /**
     * ID
     */
    private UUID id;

    /**
     * ID from Beers_API
     */
    private Long beerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeersId beersId = (BeersId) o;
        return Objects.equals(id, beersId.id) && Objects.equals(beerId, beersId.beerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beerId);
    }
}
