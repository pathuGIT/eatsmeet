package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Curry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurryRepo extends JpaRepository<Curry, Integer> {
}
