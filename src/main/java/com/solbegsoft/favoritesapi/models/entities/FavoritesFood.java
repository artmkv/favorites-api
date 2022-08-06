package com.solbegsoft.favoritesapi.models.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FavoritesFood {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "text")
    private String text;

    @Column(name = "rate")
    private Integer rate;
}
