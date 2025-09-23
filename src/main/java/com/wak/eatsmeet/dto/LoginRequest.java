package com.wak.eatsmeet.dto;

import jakarta.validation.constraints.NotBlank;
public class LoginRequest {

    @NotBlank(message = "Name is required")
    private String login;

    @NotBlank(message = "Name is required")
    private String password;

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
