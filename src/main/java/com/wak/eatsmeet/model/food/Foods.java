package com.wak.eatsmeet.model.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int id;
    @NotBlank(message = "Name can't be empty.")
    private String name;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;
    @NotBlank(message = "Details can't be empty.")
    private String details;

    private String img_url;

    @OneToMany(mappedBy = "foods", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<FoodsCurry> foodsCurries = new HashSet<>();

    public Foods(int id, String name, double price, String details, String img_url, Set<FoodsCurry> foodsCurries) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.img_url = img_url;
        this.foodsCurries = foodsCurries;
    }

    public Foods() {
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Set<FoodsCurry> getFoodsCurries() {
        return foodsCurries;
    }

    public void setFoodsCurries(Set<FoodsCurry> foodsCurries) {
        this.foodsCurries = foodsCurries;
    }
}
