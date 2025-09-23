package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> getProfile(Authentication authentication){
        String username = authentication.getName(); // usually the email/contact (subject)
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(new ApiResponse<>("Successfully get profile", Map.of("username", username, "role", role)));
    }
}
