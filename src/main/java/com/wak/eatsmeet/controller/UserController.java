package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody Users users){
        try {
            Users updatedUser = userService.updateUser(userId, users);
            return ResponseEntity.ok(new ApiResponse<Users>("Updated User Successfully", updatedUser));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }
}
