package com.wak.eatsmeet.repository.food;

import com.wak.eatsmeet.model.food.Curry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurryRepo extends JpaRepository<Curry, Integer> {

    boolean existsByName(String name);

    List<Object> findByName(String name);

    List<Curry> findAllByNameContaining(String name);
}
