package com.wak.eatsmeet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bite_id")
    private int id;
    private String name;
    private double price;
    private String details;
    private String img_url;
}
