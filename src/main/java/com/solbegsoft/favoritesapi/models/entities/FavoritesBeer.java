package com.solbegsoft.favoritesapi.models.entities;


import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(BeersId.class)
//@Table(name = "favorites_beer")
@Table(name = "favorites_beer", schema = "favorites")
public class FavoritesBeer {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * Id_Beer from Beers_API
     */
    @Id
    @Column(name = "beer_api_id")
    private Long foreignBeerApiId;

    /**
     * User Id
     */
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    /**
     * Rating
     */
    @Column(name = "rate")
    private Integer rate;

    /**
     * name
     */
    @Column(unique = true)
    private String name;

    /**
     * ABV
     */
    @Column
    private double abv;

    /**
     * IBU
     */
    @Column
    private double ibu;

    /**
     * EBC
     */
    @Column
    private double ebc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesBeer that = (FavoritesBeer) o;
        return Double.compare(that.abv, abv) == 0 && Double.compare(that.ibu, ibu) == 0 && Double.compare(that.ebc, ebc) == 0 && Objects.equals(id, that.id) && Objects.equals(foreignBeerApiId, that.foreignBeerApiId) && Objects.equals(userId, that.userId) && Objects.equals(rate, that.rate) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foreignBeerApiId, userId, rate, name, abv, ibu, ebc);
    }
}
