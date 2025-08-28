package com.wak.eatsmeet.service;

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
        Users users = null;

        if(loginInput.contains("@")){
            users = userRepo.findByEmail(loginInput);
            if(users == null){
                users = employeeRepo.findByContact(loginInput);
            }
        }
        else{
            users = userRepo.findByContact(loginInput);
            if(users == null){
                users = employeeRepo.findByContact(loginInput);
            }
        }

        if (users == null) {
            throw new UsernameNotFoundException("User not found with: " + loginInput);
        }

        return User.builder()
                .username(users.getEmail() != null ? users.getEmail() : users.getContact())
                .password(users.getPassword())
                .roles(String.valueOf(users.getRole()))
                .build();
    }
}
