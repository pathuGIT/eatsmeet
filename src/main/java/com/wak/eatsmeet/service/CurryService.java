package com.wak.eatsmeet.service;

import com.wak.eatsmeet.exception.ResourceAlreadyExistException;
import com.wak.eatsmeet.exception.ResourceNotFoundException;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.repository.food.CurryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurryService {
    @Autowired
    private CurryRepo curryRepo;


    public Curry addCurry(Curry curryRequest) {
        if (curryRepo.existsByName(curryRequest.getName()))
            throw new ResourceAlreadyExistException("curry with name '" + curryRequest.getName() + "' already exists");
        return curryRepo.save(curryRequest);
    }

    public List<Curry> getAll() {
        return curryRepo.findAll();
    }

    public Curry getById(int id) {
        return curryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(" curry with id '" + id + " not found."));
    }

    public List<Curry> getByName(String name) {
        return curryRepo.findAllByNameContaining(name);
    }

    @Transactional
    public Curry updateCurry(int id, Curry curryRequest) {
        Curry curry = curryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(" curry with id '" + id + " not found."));

        if (StringUtils.hasText(curryRequest.getName())) curry.setName(curryRequest.getName());

        if (StringUtils.hasText(curryRequest.getDetails())) curry.setDetails(curryRequest.getDetails());

        curry.setPrice(curryRequest.getPrice());
        return curryRepo.save(curry);
    }

    public void deleteById(int id) {
        if (!curryRepo.existsById(id)) throw new ResourceNotFoundException(" curry with id '" + id + " not found.");
        curryRepo.deleteById(id);
    }

    public List<Curry> getAll(int min, int max) {
        return curryRepo.findAll().stream().filter(curry -> curry.getPrice() >= min && curry.getPrice() <= max).collect(Collectors.toList());
    }
}
