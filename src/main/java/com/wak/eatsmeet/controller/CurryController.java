package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.service.CurryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/curry")
public class CurryController {
    @Autowired
    private CurryService curryService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> addCurry(@Valid @RequestBody Curry curryRequest, BindingResult result){
        if (result.hasErrors()) {
            // Collect error messages
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(new ApiResponse<List<String>>( "Invalid inputs",errors));
        }

        try {
            Curry res = curryService.addCurry(curryRequest);
            return ResponseEntity.ok(new ApiResponse<Curry>("Curry added successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }
}
