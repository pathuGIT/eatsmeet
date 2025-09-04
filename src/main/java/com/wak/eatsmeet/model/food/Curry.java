package com.wak.eatsmeet.model.food;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;
    @NotBlank(message = "Details is required")
    private String details;

    private String img_url;

    @OneToMany(mappedBy = "curry", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodsCurry> foodsCurries = new HashSet<>();
}
