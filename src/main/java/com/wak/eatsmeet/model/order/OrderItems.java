package com.wak.eatsmeet.model.order;

import com.wak.eatsmeet.model.food.enums.ItemTypes;
import jakarta.persistence.*;

import java.util.Date;

@Entity
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

    public OrderItems(int id, int item_id, ItemTypes itemTypes, double quantity, double price, Date created_date, boolean selected, Orders orders) {
        this.id = id;
        this.item_id = item_id;
        this.itemTypes = itemTypes;
        this.quantity = quantity;
        this.price = price;
        this.created_date = created_date;
        this.selected = selected;
        this.orders = orders;
    }

    public OrderItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public ItemTypes getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(ItemTypes itemTypes) {
        this.itemTypes = itemTypes;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
