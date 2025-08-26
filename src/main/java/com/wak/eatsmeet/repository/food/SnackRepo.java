package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackRepo extends JpaRepository<Snacks, Integer> {
}
