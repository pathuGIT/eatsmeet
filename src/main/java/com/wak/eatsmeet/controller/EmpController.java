package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.dto.EmployeeResponse;
import com.wak.eatsmeet.dto.UpdateRoleRequest;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import com.wak.eatsmeet.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllEmp(){
        try {
            List<EmployeeResponse>  result = employeeService.getAllEmp();
            return ResponseEntity.ok(new ApiResponse<List<EmployeeResponse>>("Successfully get all employees.", result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getEmpByName(@RequestParam String name){
        try {
            List<EmployeeResponse> emp = employeeService.getEmpByName(name);
            return ResponseEntity.ok(new ApiResponse<List<EmployeeResponse>>("Successfully get employee by name.", emp));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @GetMapping("/search/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getEmpById(@PathVariable int id){
        try {
            EmployeeResponse emp = employeeService.getEmpById(id);
            return ResponseEntity.ok(new ApiResponse<EmployeeResponse>("Successfully get employee by name.", emp));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteEmpById(@PathVariable int id){
        try {
            employeeService.deleteEmpById(id);
            return ResponseEntity.ok(new ApiResponse<Integer>("Successfully delete employee by id: "+ id, null));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateEmpById(@PathVariable int id, @RequestBody EmployeeResponse emp){
        try {
            EmployeeResponse res =  employeeService.updateEmpById(id, emp);
            return ResponseEntity.ok(new ApiResponse<EmployeeResponse>("Successfully update employee by id: "+ id, res));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("An unexpected error occurred", null));
        }
    }

    @PutMapping("/role/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateEmpRole(
            @PathVariable int id,
            @Valid @RequestBody UpdateRoleRequest request,
            BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(new ApiResponse<>("Invalid Input", errors));
        }

        try {
            EmployeeResponse res = employeeService.updateEmpRole(id, request.getRole());
            return ResponseEntity.ok(new ApiResponse<>("Successfully updated employee role by id: " + id, res));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("An unexpected error occurred", null));
        }
    }

}
