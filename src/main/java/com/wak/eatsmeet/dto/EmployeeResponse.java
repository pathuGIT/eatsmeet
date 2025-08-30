package com.wak.eatsmeet.dto;

import com.wak.eatsmeet.model.user.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
