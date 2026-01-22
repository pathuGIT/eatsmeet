package com.wak.eatsmeet.dto.cart;


import com.wak.eatsmeet.model.food.enums.Times;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartUserRequestDTO {
    private int user_id;
    private LocalDate date;
    private Times times;
    private  int food_id;
}
