package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.food.FoodIdListDTO;
import com.wak.eatsmeet.dto.food.FoodsCurryDTO;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.model.food.FoodsCurry;
import com.wak.eatsmeet.model.food.enums.Times;
import com.wak.eatsmeet.repository.food.CurryRepo;
import com.wak.eatsmeet.repository.food.FoodCurryRepo;
import com.wak.eatsmeet.repository.food.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurryFoodService {
    @Autowired
    private FoodCurryRepo foodCurryRepo;
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private CurryRepo curryRepo;

    public List<FoodsCurryDTO> assignFoodToCurry(int curryId, FoodIdListDTO foodDto) {

        List<FoodsCurry> foodsCurryList = new ArrayList<>();

        for (int id : foodDto.getFoodId()) {
            Foods foods = foodRepo.findById(id).orElseThrow();
            Curry curry = curryRepo.findById(curryId).orElseThrow();

            if (foodCurryRepo.existsByCurryAndFoodsAndDateAndTimes(curry, foods, foodDto.getDate(), Times.valueOf(foodDto.getTimes()))) {
                throw new IllegalArgumentException("Food already exists for this curry with time and date");
            }

            FoodsCurry foodsCurry = new FoodsCurry();
            foodsCurry.setFoods(foods);
            foodsCurry.setCurry(curry);
            foodsCurry.setDate(foodDto.getDate());
            foodsCurry.setTimes(Times.valueOf(foodDto.getTimes()));
            foodsCurry.setImg_url(foodDto.getImg_url());

            foodsCurryList.add(foodsCurry);
        }

         List<FoodsCurry> list = foodCurryRepo.saveAll(foodsCurryList);
        //return foodsCurries.stream().map(FoodsCurry::toDTO).toList();
        List<FoodsCurryDTO> dtos = foodsCurryList.stream().map(fc -> {
            FoodsCurryDTO dto = new FoodsCurryDTO();
            dto.setId(fc.getId());
            dto.setFoodName(fc.getFoods().getName());
            dto.setCurryName(fc.getCurry().getName());
            dto.setDate(fc.getDate());
            dto.setTimes(fc.getTimes());
            dto.setImgUrl(fc.getImg_url());
            return dto;
        }).collect(Collectors.toList());

        return dtos;
    }

    public List<Foods> getFoodsByCurryId(int curryId) {
        System.out.println("t2");
        if(!foodCurryRepo.existsByCurryId(curryId)){
            throw new IllegalArgumentException("No Foods found for this curry");
        }
        System.out.println("t3");
        List<Foods> foodsCurryList = foodCurryRepo.findDistinctFoodsByCurryId(curryId);

        return foodsCurryList;
    }
}
