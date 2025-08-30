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

    private EmployeeResponse mapToResponse(Employees employees) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employees.getId());
        employeeResponse.setName(employees.getName());
        employeeResponse.setNic(employees.getNic());
        employeeResponse.setContact(employees.getContact());
        employeeResponse.setBod(employees.getBod());
        employeeResponse.setAddress(employees.getAddress());
        employeeResponse.setActive(employees.getActive());
        employeeResponse.setRole(employees.getRole());
        employeeResponse.setImg_url(employees.getImg_url());
        employeeResponse.setEmail(employees.getEmail());
        return employeeResponse;
    }

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
            employeeResponses.add(mapToResponse(employees1));
        });

        return employeeResponses;
    }

    public List<EmployeeResponse> getEmpByName(String name) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        List<Employees> employees = employeeRepo.findAllByNameContaining(name);

        employees.forEach(employees1 -> {
            employeeResponses.add(mapToResponse(employees1));
        });

        if (employeeResponses.isEmpty()) {
            throw new IllegalArgumentException("No users found with name: " + name);
        }
        return employeeResponses;
    }

    public EmployeeResponse getEmpById(int id) {
        Employees employees = employeeRepo.findById(id).orElseThrow(
                        ()-> new IllegalArgumentException("Emp not found with ID: " + id));

        return mapToResponse(employees);
    }

    public void deleteEmpById(int id) {
        Employees employees = employeeRepo.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Emp not found with ID: " + id));

        employeeRepo.delete(employees);
    }

    public EmployeeResponse updateEmpById(int id, EmployeeResponse emp) {
        Employees employees = employeeRepo.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Emp not found with ID: " + id));

        employees.setName(emp.getName());
        employees.setNic(emp.getNic());
        employees.setContact(emp.getContact());
        employees.setBod(emp.getBod());
        employees.setAddress(emp.getAddress());
        employees.setActive(emp.getActive());
        employees.setImg_url(emp.getImg_url());
        employees.setEmail(emp.getEmail());

        return mapToResponse(employeeRepo.save(employees));
    }
}
