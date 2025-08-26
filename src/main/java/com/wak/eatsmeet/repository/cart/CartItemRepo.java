package com.wak.eatsmeet.repository.cart;

import com.wak.eatsmeet.model.cart.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems, Integer> {
}
