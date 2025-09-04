package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.repository.food.CurryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurryService {
    @Autowired
    private CurryRepo curryRepo;


    public Curry addCurry(Curry curryRequest) {
        if(curryRepo.existsByName(curryRequest.getName())) {
            throw new IllegalArgumentException("This Curry name already exists");
        }

        return curryRepo.save(curryRequest);
    }
}
