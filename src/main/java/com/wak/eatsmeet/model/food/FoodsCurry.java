package com.wak.eatsmeet.model.food;

import com.wak.eatsmeet.model.food.enums.Times;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
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

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Times times;

    private String img_url;

    public FoodsCurry(int id, Foods foods, Curry curry, LocalDate date, Times times, String img_url) {
        this.id = id;
        this.foods = foods;
        this.curry = curry;
        this.date = date;
        this.times = times;
        this.img_url = img_url;
    }

    public FoodsCurry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

    public Curry getCurry() {
        return curry;
    }

    public void setCurry(Curry curry) {
        this.curry = curry;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
