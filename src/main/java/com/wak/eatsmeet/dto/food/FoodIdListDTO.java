package com.wak.eatsmeet.dto.food;

import com.wak.eatsmeet.model.food.enums.Times;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FoodIdListDTO {
    private List<Integer> foodId;
    private LocalDate date;
    private String times;
    private String img_url;

    public FoodIdListDTO(List<Integer> foodId, LocalDate date, String times, String img_url) {
        this.foodId = foodId;
        this.date = date;
        this.times = times;
        this.img_url = img_url;
    }

    public FoodIdListDTO() {
    }

    public List<Integer> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Integer> foodId) {
        this.foodId = foodId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
