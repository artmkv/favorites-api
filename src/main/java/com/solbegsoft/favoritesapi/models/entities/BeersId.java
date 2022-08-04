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
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    private Integer beerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeersId customId = (BeersId) o;
        return Objects.equals(id, customId.id) && Objects.equals(beerId, customId.beerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beerId);
    }
}
