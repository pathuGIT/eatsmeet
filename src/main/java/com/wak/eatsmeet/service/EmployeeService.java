package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Roles;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
