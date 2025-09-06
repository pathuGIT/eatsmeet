package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.repository.food.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodRepo foodRepo;

    public Foods addFood(Foods food) {
        if(foodRepo.existsByName(food.getName())){
            throw new RuntimeException("Food already exists");
        }
        return foodRepo.save(food);
    }
}
