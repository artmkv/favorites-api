package com.solbegsoft.favoritesapi.models.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import java.util.UUID;

/**
 * Favorites food Entity
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites_food", schema = "beers")
public class FavoritesFood {

    /**
     * ID
     */
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

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
}
