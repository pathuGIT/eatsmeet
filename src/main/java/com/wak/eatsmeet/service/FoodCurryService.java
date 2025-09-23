package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.food.CurryListDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCurryService {
    @Autowired
    private FoodCurryRepo foodCurryRepo;
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private CurryRepo curryRepo;


    public List<FoodsCurryDTO> assignCurryToFood(int foodId, CurryListDTO curryDto) {
        List<FoodsCurry> foodsCurryList = new ArrayList<>();

        for (int id : curryDto.getCurryId()) {
            Curry curry = curryRepo.findById(id).orElseThrow();
            Foods foods = foodRepo.findById(foodId).orElseThrow();

            if (foodCurryRepo.existsByCurryAndFoodsAndDateAndTimes(curry, foods, curryDto.getDate(), Times.valueOf(curryDto.getTimes()))) {
                throw new IllegalArgumentException("Curry already exists for this food with time and date");
            }

            FoodsCurry foodsCurry = new FoodsCurry();
            foodsCurry.setFoods(foods);
            foodsCurry.setCurry(curry);
            foodsCurry.setDate(curryDto.getDate());
            foodsCurry.setTimes(Times.valueOf(curryDto.getTimes()));
            foodsCurry.setImg_url(curryDto.getImg_url());

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

    public List<Curry> getCurryByFoodsId(int foodId, String date, String time) {
        LocalDate localDate = LocalDate.parse(date);
        Times timesEnum = Times.valueOf(time.toUpperCase()); // convert string to enum

        if(!foodCurryRepo.existsByFoods_IdAndDateAndTimes(foodId, localDate, timesEnum)){
            throw new IllegalArgumentException("No Curry found for this food on given date and time");
        }

        return foodCurryRepo.findDistinctCurryByFoods_IdAndDateAndTimes(foodId, localDate, timesEnum);
    }

//    public List<FoodsCurry> getFoodsCurryForDate(String date){
//        return foodCurryRepo.findAllByDate(date);
//    }
}
