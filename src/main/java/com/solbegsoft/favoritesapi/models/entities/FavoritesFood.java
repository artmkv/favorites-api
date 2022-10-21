package com.solbegsoft.favoritesapi.models.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity Favorites food
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "favorites_food")
@Table(name = "favorites_food", schema = "favorites")
public class FavoritesFood {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * User Id
     */
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    /**
     * Id_Beer from Beers_API
     */
    @Column(name = "beer_api_id")
    private Long foreignBeerApiId;

    /**
     * Descriprion of Food
     */
    @Column(name = "text", length = 512)
    private String text;

    /**
     * Rate of food
     */
    @DecimalMax(value = "5")
    @Column(name = "rate")
    private Integer rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesFood that = (FavoritesFood) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(text, that.text) && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, rate);
    }
}
