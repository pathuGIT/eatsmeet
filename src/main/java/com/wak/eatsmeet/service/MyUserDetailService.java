package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.LoadUser;
import com.wak.eatsmeet.dto.LoadUserRole;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import com.wak.eatsmeet.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String loginInput) throws UsernameNotFoundException {

        LoadUser loadUser = null;

        if (loginInput.contains("@")) {
            // Email login
            Users user = userRepo.findByEmail(loginInput);
            if (user != null) {
                loadUser = mapToLoadUser(user);
            } else {
                Employees emp = employeeRepo.findByEmail(loginInput);
                if (emp != null) {
                    loadUser = mapToLoadUser(emp);
                }
            }
        } else {
            // Contact login
            Users user = userRepo.findByContact(loginInput);
            if (user != null) {
                loadUser = mapToLoadUser(user);
            } else {
                Employees emp = employeeRepo.findByContact(loginInput);
                if (emp != null) {
                    loadUser = mapToLoadUser(emp);
                }
            }
        }

        if (loadUser == null) {
            throw new UsernameNotFoundException("User not found with: " + loginInput);
        }

        System.out.println("Authenticated user: " + (loadUser.getEmail() != null ? loadUser.getEmail() : loadUser.getContact()));

        return User.builder()
                //.username(loadUser.getEmail() != null ? loadUser.getEmail() : loadUser.getContact())
                .username(String.valueOf(loadUser.getId()))
                .password(loadUser.getPassword())
                .roles(String.valueOf(loadUser.getRole()))
                .build();
    }

    //  Helper methods to reduce repetition
    private LoadUser mapToLoadUser(Users user) {
        LoadUser loadUser = new LoadUser();
        loadUser.setId(user.getId());
        loadUser.setEmail(user.getEmail());
        loadUser.setContact(user.getContact());
        loadUser.setRole(LoadUserRole.valueOf(user.getRole().name()));
        loadUser.setPassword(user.getPassword());
        loadUser.setRefresh_token(user.getRefresh_token());
        return loadUser;
    }

    private LoadUser mapToLoadUser(Employees emp) {
        LoadUser loadUser = new LoadUser();
        loadUser.setId(emp.getId());
        loadUser.setEmail(emp.getEmail());
        loadUser.setContact(emp.getContact());
        loadUser.setRole(LoadUserRole.valueOf(emp.getRole().name()));
        loadUser.setPassword(emp.getPassword());
        loadUser.setRefresh_token(emp.getRefresh_token());
        return loadUser;
    }
}
