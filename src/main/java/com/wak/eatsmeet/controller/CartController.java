package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.cart.CartResponse;
import com.wak.eatsmeet.dto.cart.CartUserRequestDTO;
import com.wak.eatsmeet.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("add-food")
    public List<CartResponse> addFoodIntoCart(@RequestBody CartUserRequestDTO cartUserRequestDTO) {
        return cartService.addCartList(cartUserRequestDTO);
    }

}
