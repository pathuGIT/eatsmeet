package com.wak.eatsmeet.repository.cart;

import com.wak.eatsmeet.model.cart.Cart;
import com.wak.eatsmeet.model.cart.CartItems;
import com.wak.eatsmeet.model.food.enums.ItemTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItems, Integer> {

    Optional<CartItems> findByCartAndItemIdAndItemTypes(
            Cart cart,
            int itemId,
            ItemTypes itemTypes
    );

    void deleteAllByCart(Cart cart);

}
