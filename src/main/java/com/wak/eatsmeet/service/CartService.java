package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.cart.CartResponse;
import com.wak.eatsmeet.dto.cart.CartUserRequestDTO;
import com.wak.eatsmeet.model.cart.Cart;
import com.wak.eatsmeet.model.cart.CartItems;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.model.food.Foods;
import com.wak.eatsmeet.model.food.enums.ItemTypes;
import com.wak.eatsmeet.repository.cart.CartItemRepo;
import com.wak.eatsmeet.repository.cart.CartRepo;
import com.wak.eatsmeet.repository.food.FoodCurryRepo;
import com.wak.eatsmeet.repository.food.FoodRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    private  FoodCurryRepo foodCurryRepo;

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Transactional
    public List<CartResponse> addCartList(CartUserRequestDTO dto) {

        Cart cart = cartRepo.findByUsers_Id(dto.getUser_id())
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        List<Curry> curries = foodCurryRepo
                .findDistinctCurryByFoods_IdAndDateAndTimes(
                        dto.getFood_id(),
                        dto.getDate(),
                        dto.getTimes()
                );

        if (curries.isEmpty()) {
            throw new RuntimeException("No curries mapped for this food");
        }

        Foods food = foodRepo.findById(dto.getFood_id())
                .orElseThrow(() -> new RuntimeException("Food not found"));

        List<CartItems> cartItemsList = new ArrayList<>();
        List<CartResponse> responseList = new ArrayList<>();

        for (Curry curry : curries) {

            CartItems cartItem = new CartItems();
            cartItem.setCart(cart);
            cartItem.setItem_id(dto.getFood_id());
            cartItem.setItemTypes(ItemTypes.FOODS);
            cartItem.setCurry_id(curry.getId());
            cartItem.setQuantity(1);
            cartItem.setSelected(true);
            cartItem.setCreated_date(Date.from(Instant.now()));
            cartItem.setPrice(food.getPrice());

            cartItemsList.add(cartItem);

            CartResponse cartResponse = new CartResponse();
            cartResponse.setCartId(cart.getId());
            cartResponse.setItems(cart.getCartItems());
            cartResponse.setTotalAmount(food.getPrice());

            responseList.add(cartResponse);
        }

        cartItemRepo.saveAll(cartItemsList);

        return responseList;
    }
}