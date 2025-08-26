package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Bites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiteRepo extends JpaRepository<Bites, Integer> {
}
