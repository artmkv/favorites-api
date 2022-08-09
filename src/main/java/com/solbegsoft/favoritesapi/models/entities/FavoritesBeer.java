package com.solbegsoft.favoritesapi.models.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity Favorites Beer
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@IdClass(BeersId.class)
@Table(name = "favorites")
public class FavoritesBeer {

    /**
     * ID
     */
    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    @Id
    @Column(name = "beer_api_id", nullable = false, updatable = false)
    private Long beerId;

    /**
     * User Id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Rating
     */
    @Column(name = "rate")
    private Integer rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesBeer that = (FavoritesBeer) o;
        return Objects.equals(id, that.id) && Objects.equals(beerId, that.beerId) && Objects.equals(userId, that.userId) && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beerId, userId, rate);
    }
}
