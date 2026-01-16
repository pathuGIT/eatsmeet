package com.wak.eatsmeet.repository.cart;

import com.wak.eatsmeet.model.cart.Cart;
import com.wak.eatsmeet.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUsers(Users user);

}
