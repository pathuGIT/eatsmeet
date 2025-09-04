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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> updateCurry(@Valid @PathVariable int id, @RequestBody Curry curryRequest, BindingResult result){
        if (result.hasErrors()) {
            // Collect error messages
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(new ApiResponse<List<String>>( "Invalid inputs",errors));
        }

        try {
            Curry res = curryService.updateCurry(id,curryRequest);
            return ResponseEntity.ok(new ApiResponse<Curry>("Curry updated successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @DeleteMapping("/deleteId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        try {
            Curry res = curryService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<Curry>("curry deleted successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCurry(){
        try {
            List<Curry> res = curryService.getAll();
            return ResponseEntity.ok(new ApiResponse<List<Curry>>("Get all curries successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/searchId/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<?> getCurryById(@PathVariable int id){
        try {
            Curry res = curryService.getById(id);
            return ResponseEntity.ok(new ApiResponse<Curry>("Get curry successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<?> getCurriesByName(@PathVariable String name){
        try {
            List<Curry> res = curryService.getByName(name);
            return ResponseEntity.ok(new ApiResponse<List<Curry>>("Found by curry name successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/searchPrice")
    public ResponseEntity<?> findAllCurryWithRangePrice(@RequestParam int min, @RequestParam int max){
        try {
            List<Curry> res = curryService.getAll(min, max);
            return ResponseEntity.ok(new ApiResponse<List<Curry>>("Get all curries successfully", res));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

}
