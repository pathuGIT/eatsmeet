package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.repository.food.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Foods> getAll() {
        if(foodRepo.findAll().isEmpty()) {
            throw new IllegalArgumentException("Empty foods.");
        }
        return foodRepo.findAll();
    }

    public Foods getById(int id) {
        if(!foodRepo.existsById(id)) {
            throw new IllegalArgumentException("No Food found with id: " + id);
        }
        return foodRepo.findById(id).get();
    }

    public Foods updateFood(int id, Foods result) {
        if(!foodRepo.existsById(id)){
            throw new IllegalArgumentException("No Food found with id: " + id);
        }

        Foods food = foodRepo.findById(id).get();

        food.setName(result.getName());
        food.setPrice(result.getPrice());
        food.setDetails(result.getDetails());
        food.setImg_url(result.getImg_url());

        return foodRepo.save(food);
    }

    public Foods deleteById(int id) {
        if(!foodRepo.existsById(id)){
            throw new IllegalArgumentException("No curry found with id: " + id);
        }
        Foods foods = foodRepo.findById(id).get();
        foodRepo.deleteById(id);
        return foods;
    }

    public List<Foods> getByName(String name) {
        List<Foods> list = foodRepo.findAllByNameContaining(name);

        if(list.isEmpty()){
            throw new IllegalArgumentException("No Foods found with name: " + name);
        }

        return list;
    }
}
