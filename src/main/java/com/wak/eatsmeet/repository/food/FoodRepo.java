package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<Foods, Integer> {
}
