package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import com.wak.eatsmeet.service.EmployeeService;
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
@RequestMapping("/api/emp")
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addEmp(@Valid @RequestBody Employees emp, BindingResult result){ //add new emp by admin
        try {
            if (result.hasErrors()) {
                // Collect error messages
                List<String> errors = result.getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList();

                return ResponseEntity.badRequest().body(errors);
            }

            Employees res = employeeService.register(emp);
            return ResponseEntity.ok(new ApiResponse<Employees>("Successfully register user.", res));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
