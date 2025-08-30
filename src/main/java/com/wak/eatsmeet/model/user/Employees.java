package com.wak.eatsmeet.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

}
