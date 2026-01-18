package com.wak.eatsmeet.dto.food.curryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurryResponse {
    private int id;
    private String name;
    private Double price;
    private String details;
    private String imageUrl;
}
