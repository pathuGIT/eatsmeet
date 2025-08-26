package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.dto.UserRegisterResponse;
import com.wak.eatsmeet.model.user.UserRole;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody Users users, BindingResult result){
        System.out.println("test1");
        if (result.hasErrors()) {
            // Collect error messages
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(errors);
        }

        try {
            users.setRole(UserRole.USER);
            users.setActive(true);
            users.setImg_url(null);

            UserRegisterResponse res = authService.userRegister(users);
            ApiResponse<UserRegisterResponse> response = new ApiResponse<>();
            response.setMessage("User registered successfully!");
            response.setData(res);
            return ResponseEntity.ok(response);


        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }
}
