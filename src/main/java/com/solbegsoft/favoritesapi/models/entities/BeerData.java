package com.solbegsoft.favoritesapi.models.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RootBeer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class BeerData {

    /**
     * name
     */
    @Column
    private String name;
    /**
     * tagline
     */
    @Column
    private String tagline;
    /**
     * First brewed
     */
    @Column
    private String firstBrewed;
    /**
     * Description
     */
    @Column
    private String description;
    /**
     * Image URL
     */
    @Column
    private String imageUrl;
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
     * Target_fg
     */
    @Column
    private double targetFg;
    /**
     * Target_fg
     */
    @Column
    private double targetOg;
    /**
     * EBC
     */
    @Column
    private double ebc;
    /**
     * SRM
     */
    @Column
    private double srm;
    /**
     * PH
     */
    @Column
    private double ph;
    /**
     * Attenuation level
     */
    @Column
    private double attenuationLevel;
//    /**
//     * food array
//     */
//    @Column(name = "food")
//    private ArrayList<String> foodPairing;
    /**
     * Brewers tips
     */
    @Column
    private String brewersTips;
    /**
     * Contributed by
     */
    @Column
    private String contributedBy;
}
