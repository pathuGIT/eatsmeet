package com.wak.eatsmeet.repository.user;

import com.wak.eatsmeet.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    boolean existsByNic(String nic);

}
