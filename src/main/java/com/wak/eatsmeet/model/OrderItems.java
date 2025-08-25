package com.wak.eatsmeet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int id;


    private int item_id;

    @Enumerated(EnumType.STRING)
    private ItemTypes itemTypes;

    private double quantity;
    private double price;
    private Date created_date;
    private boolean selected;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders orders;
}
