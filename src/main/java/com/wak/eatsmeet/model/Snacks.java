package com.wak.eatsmeet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Snacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_id")
    private int id;
    private String name;
    private double stock;
    private double price;
    private boolean scale;
    private String description;
    private Date mnf_date;
    private Date exp_date;
    private String brand;
    private String img_url;
}
