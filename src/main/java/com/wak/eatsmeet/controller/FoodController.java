package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    // Create Food
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> addFood(@Valid @RequestBody Foods food, BindingResult result) {
        if (result.hasErrors()) {
            // Collect error messages
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(new ApiResponse<List<String>>( "Invalid inputs",errors));
        }

        try {
            Foods foodResponse = foodService.addFood(food);
            return ResponseEntity.ok(new ApiResponse<Foods>("New Food Add Successfully.", foodResponse));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    // Get All Foods
    @GetMapping("/all")
    public ResponseEntity<?> getAllFood(){
        try {
            List<Foods> res = foodService.getAll();
            return ResponseEntity.ok(new ApiResponse<List<Foods>>("Get all foods successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    // Get Food by ID
    @GetMapping("/searchBy/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> getCurryById(@PathVariable int id){
        try {
            Foods res = foodService.getById(id);
            return ResponseEntity.ok(new ApiResponse<Foods>("Get curry successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> updateFood(@Valid @PathVariable int id, @RequestBody Foods foods, BindingResult result){
        if (result.hasErrors()) {
            // Collect error messages
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(new ApiResponse<List<String>>( "Invalid inputs",errors));
        }

        try {
            Foods res = foodService.updateFood(id, foods);
            return ResponseEntity.ok(new ApiResponse<Foods>("Food updated successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }
}
