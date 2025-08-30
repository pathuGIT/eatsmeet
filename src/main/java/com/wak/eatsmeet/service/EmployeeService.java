package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.EmployeeResponse;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Roles;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Employees register(Employees emp) {

            if(employeeRepo.existsByEmail(emp.getEmail())){
                throw  new IllegalArgumentException("This user email is already have..");
            }
            if(employeeRepo.existsByContact(emp.getContact())){
                throw new IllegalArgumentException("This user Contact is already have..");
            }
            if(employeeRepo.existsByNic(emp.getNic())){
                throw new IllegalArgumentException("This user MIC is already have..");
            }

            emp.setPassword(bCryptPasswordEncoder.encode(emp.getPassword()));
            emp.setRole(Roles.EMPLOYEE);
            emp.setActive(true);

            return employeeRepo.save(emp);
    }

    public List<EmployeeResponse> getAllEmp() {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        List<Employees> employees = employeeRepo.findAll();
        employees.forEach(employees1 -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employees1.getId());
            employeeResponse.setName(employees1.getName());
            employeeResponse.setNic(employees1.getNic());
            employeeResponse.setContact(employees1.getContact());
            employeeResponse.setBod(employees1.getBod());
            employeeResponse.setAddress(employees1.getAddress());
            employeeResponse.setActive(employees1.getActive());
            employeeResponse.setRole(employees1.getRole());
            employeeResponse.setImg_url(employees1.getImg_url());
            employeeResponse.setEmail(employees1.getEmail());
            employeeResponses.add(employeeResponse);
        });

        return employeeResponses;
    }
}
