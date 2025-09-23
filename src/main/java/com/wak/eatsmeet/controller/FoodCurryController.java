package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.dto.food.CurryListDTO;
import com.wak.eatsmeet.dto.food.FoodIdListDTO;
import com.wak.eatsmeet.dto.food.FoodsCurryDTO;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.model.food.FoodsCurry;
import com.wak.eatsmeet.service.CurryFoodService;
import com.wak.eatsmeet.service.FoodCurryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/food")
public class FoodCurryController {

    @Autowired
    private FoodCurryService foodCurryService;

    @PostMapping("/{foodId}/add-curry")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> addCurryToFood(@PathVariable int foodId, @RequestBody CurryListDTO curryDto) {
        try {
            List<FoodsCurryDTO> response = foodCurryService.assignCurryToFood(foodId, curryDto);
            return ResponseEntity.ok(new ApiResponse<List<FoodsCurryDTO>>("Foods assigned to curry successfully", response));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>(e.getMessage(), null));
        }
    }

    //Get All curry for a food
    @GetMapping("/{foodId}/date/{date}/time/{time}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> getCurryByFoodsId(@PathVariable int foodId, @PathVariable String date, @PathVariable String time){
        try {
            List<Curry> response = foodCurryService.getCurryByFoodsId(foodId, date, time);
            String message = String.format(
                    "Curries fetched successfully for foodId: %d on date: %s at time: %s",
                    foodId, date, time
            );
            return ResponseEntity.ok(new ApiResponse<List<Curry>>(message, response));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>(e.getMessage(), null));
        }
    }

//    @GetMapping("/all")
//    public ResponseEntity<?> getAllFoodCurryByDate(@RequestBody Map<String, String> request){
//        String date = request.get("date");
//        List<FoodsCurry> response = foodCurryService.getFoodsCurryForDate(date);
//        return ResponseEntity.ok(new ApiResponse<List<FoodsCurry>>("Foods fetched successfully", response));
//    }

}
