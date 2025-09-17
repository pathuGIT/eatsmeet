package com.wak.eatsmeet.service;

import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Users updateUser(int userId, Users users) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found for this id: " + userId));

        user.setName(users.getName());
        user.setNic(users.getNic());
        user.setContact(users.getContact());
        user.setImg_url(users.getImg_url());
        user.setEmail(users.getEmail());
        user.setActive(users.getActive());

        return userRepo.save(user);
    }
}
