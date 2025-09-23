package com.wak.eatsmeet.dto;

public class LoadUser {
    private int id;
    private String email;
    private String contact;
    private String password;
    private LoadUserRole role;
    private String refresh_token;

    public LoadUser(int id, String email, String contact, String password, LoadUserRole role, String refresh_token) {
        this.id = id;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.role = role;
        this.refresh_token = refresh_token;
    }

    public LoadUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoadUserRole getRole() {
        return role;
    }

    public void setRole(LoadUserRole role) {
        this.role = role;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
