package com.wak.eatsmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoadUser {
    private int id;
    private String email;
    private String contact;
    private String password;
    private LoadUserRole role;
    private String refresh_token;
}
