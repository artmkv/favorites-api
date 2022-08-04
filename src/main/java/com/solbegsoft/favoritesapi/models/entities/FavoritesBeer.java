package com.solbegsoft.favoritesapi.models.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "Generator", strategy = "uuid")
    @GeneratedValue(generator = "Generator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    @Id
    @Column(name = "beer_id", length = 64, nullable = false)
    private Integer beerId;

    /**
     * User Id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private Integer userId;

    /**
     * Rating
     */
    @Column(name = "rate", length = 16)
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
