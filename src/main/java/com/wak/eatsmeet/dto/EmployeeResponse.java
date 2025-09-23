package com.wak.eatsmeet.dto;

import com.wak.eatsmeet.model.user.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class EmployeeResponse {
    private int id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private Date bod;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String nic;
    private Boolean active;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public EmployeeResponse(int id, String name, String email, String contact, String address, Date bod, Roles role, String nic, Boolean active, String img_url) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.bod = bod;
        this.role = role;
        this.nic = nic;
        this.active = active;
        this.img_url = img_url;
    }

    public EmployeeResponse() {
    }
}
