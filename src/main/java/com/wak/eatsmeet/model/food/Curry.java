package com.wak.eatsmeet.model.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
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

    @Column(name = "img_url")
    private String imageUrl;

    @OneToMany(mappedBy = "curry", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<FoodsCurry> foodsCurries = new HashSet<>();

    public Curry(int id, String name, double price, String details, String imageUrl, Set<FoodsCurry> foodsCurries) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.imageUrl = imageUrl;
        this.foodsCurries = foodsCurries;
    }

    public Curry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl =imageUrl;
    }

    public Set<FoodsCurry> getFoodsCurries() {
        return foodsCurries;
    }

    public void setFoodsCurries(Set<FoodsCurry> foodsCurries) {
        this.foodsCurries = foodsCurries;
    }
}
