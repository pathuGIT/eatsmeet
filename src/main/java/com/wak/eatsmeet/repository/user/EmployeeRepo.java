package com.wak.eatsmeet.repository.user;

import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {

    Employees findByContact(String loginInput);

    Employees findByEmail(String loginInput);

    boolean existsByEmail(String email);

    boolean existsByContact(String contact);

    boolean existsByNic(String nic);

    @Modifying
    @Transactional
    @Query("UPDATE Employees e SET e.refresh_token = :token WHERE e.email = :email")
    void updateRefreshTokenByEmail(@Param("email") String email, @Param("token") String token);
}
