package com.wak.eatsmeet.dto.cart;

import com.wak.eatsmeet.model.cart.CartItems;
import lombok.*;

import java.util.List;

@Data
public class CartResponse {

    private int cartId;
    private List<CartItems> items;
    private double totalAmount;
}