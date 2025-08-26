package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.FoodsCurry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCurryRepo extends JpaRepository<FoodsCurry, Integer> {
}
