package com.wak.eatsmeet.repository.user;

import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {

    Employees findByContact(String loginInput);

    Employees findByEmail(String loginInput);
}
