package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.model.food.FoodsCurry;
import com.wak.eatsmeet.model.food.enums.Times;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodCurryRepo extends JpaRepository<FoodsCurry, Integer> {
    Boolean existsByCurryAndFoodsAndDateAndTimes(Curry curry, Foods foods, LocalDate date, Times times);

    boolean existsByCurryId(int curryId);

    @Query("SELECT DISTINCT fc.foods FROM FoodsCurry fc WHERE fc.curry.id = :curryId")
    List<Foods> findDistinctFoodsByCurryId(@Param("curryId") int curryId);


}
