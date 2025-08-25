package com.wak.eatsmeet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodsCurry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcry_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Foods foods;

    @ManyToOne
    @JoinColumn(name = "curry_id")
    private Curry curry;

    private Date date;

    @Enumerated(EnumType.STRING)

    private Times times;
    private String img_url;
}
