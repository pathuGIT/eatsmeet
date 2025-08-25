package com.wak.eatsmeet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Curry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curry_id")
    private int id;
    private String name;
    private double price;
    private String details;
    private String img_url;

    @OneToMany(mappedBy = "curry", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodsCurry> foodsCurries = new HashSet<>();
}
