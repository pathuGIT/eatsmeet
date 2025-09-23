package com.wak.eatsmeet.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;

    @NotBlank( message = "name must required")
    private String name;

    private String email;

    @NotBlank( message = "contact must required")
    private String contact;

    @NotBlank( message = "address must required")
    private String address;

    private Date bod;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @NotBlank(message = "NIC is required")
    private String nic;

    private Boolean active;

    @NotBlank( message = "password must required")
    private String password;

    private String refresh_token;
    private String img_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBod() {
        return bod;
    }

    public void setBod(Date bod) {
        this.bod = bod;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Employees(int id, String name, String email, String contact, String address, Date bod, Roles role, String nic, Boolean active, String password, String refresh_token, String img_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.bod = bod;
        this.role = role;
        this.nic = nic;
        this.active = active;
        this.password = password;
        this.refresh_token = refresh_token;
        this.img_url = img_url;
    }

    public Employees() {
    }
}
