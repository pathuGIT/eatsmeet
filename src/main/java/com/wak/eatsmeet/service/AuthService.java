package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.UserRegisterResponse;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserRegisterResponse userRegister(Users users) {
        if(userRepo.existsByEmail(users.getEmail())){
            throw new IllegalArgumentException("This Email already exists.");
        }
        if(userRepo.existsByContact(users.getContact())){
            throw new IllegalArgumentException("This Email already exists.");
        }
        if(userRepo.existsByNic(users.getNic())){
            throw new IllegalArgumentException("This NIC number already exists.");
        }
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        //return userRepo.save(users);

        Users res = userRepo.save(users);
        UserRegisterResponse ur = new UserRegisterResponse(res.getId(), res.getName(), res.getEmail(), res.getContact());
        return ur;
    }
}
