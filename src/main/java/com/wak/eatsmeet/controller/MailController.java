package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/validate-link")
    public ResponseEntity<?> sentValidateLink(@RequestParam String email){
        try {
            emailService.sendValidateLinkToUser(email);
            return ResponseEntity.ok(new ApiResponse<String>("Validation mail sent to " + email, null) );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<String>("Error: " + e.getMessage(), null));
        }

    }


    @PostMapping("/credentials") //send credentials to sub admin
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> sentCredentials(@RequestParam String email){
        try {
            emailService.sendCredentialsToSubAdmin(email);
            return ResponseEntity.ok(new ApiResponse<String>("Register link sent to mail: " + email, null) );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<String>("Error: " + e.getMessage(), null));
        }

    }
}
