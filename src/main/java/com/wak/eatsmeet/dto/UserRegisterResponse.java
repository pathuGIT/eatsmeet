package com.wak.eatsmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterResponse {
    private double id;
    private String username;
    private String email;
    private String contact;

}
