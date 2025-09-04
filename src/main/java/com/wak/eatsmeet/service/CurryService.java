package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.repository.food.CurryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Curry> getAll() {
        if(curryRepo.findAll().isEmpty()) {
            throw new IllegalArgumentException("No curries found");
        }
        return curryRepo.findAll();
    }

    public Curry getById(int id) {
        if(!curryRepo.existsById(id)) {
            throw new IllegalArgumentException("No curry found with id: " + id);
        }
        return curryRepo.findById(id).get();
    }

    public List<Curry> getByName(String name) {
        List<Curry> list = curryRepo.findAllByNameContaining(name);

        if(list.isEmpty()){
            throw new IllegalArgumentException("No curry found with name: " + name);
        }

        return list;
    }

    public Curry updateCurry(int id, Curry curryRequest) {
        if(!curryRepo.existsById(id)){
            throw new IllegalArgumentException("No curry found with id: " + id);
        }

        Curry curry = curryRepo.findById(id).get();

        curry.setName(curryRequest.getName());
        curry.setPrice(curryRequest.getPrice());
        curry.setDetails(curryRequest.getDetails());
        curry.setImg_url(curryRequest.getImg_url());

        return curryRepo.save(curry);
    }
}
