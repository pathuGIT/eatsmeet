package com.wak.eatsmeet.dto.food;

import com.wak.eatsmeet.model.food.enums.Times;

import java.time.LocalDate;

public class FoodsCurryDTO {
    private int id;
    private String foodName;
    private String curryName;
    private LocalDate date;
    private Times times;
    private String imgUrl;

    public FoodsCurryDTO(int id, String foodName, String curryName, LocalDate date, Times times, String imgUrl) {
        this.id = id;
        this.foodName = foodName;
        this.curryName = curryName;
        this.date = date;
        this.times = times;
        this.imgUrl = imgUrl;
    }

    public FoodsCurryDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCurryName() {
        return curryName;
    }

    public void setCurryName(String curryName) {
        this.curryName = curryName;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
