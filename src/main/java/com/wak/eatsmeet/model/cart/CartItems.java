package com.wak.eatsmeet.model.cart;

import com.wak.eatsmeet.model.food.enums.ItemTypes;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int id;

    private int itemId;

    @Column(nullable = true)
    private int curry_id;

    @Enumerated(EnumType.STRING)
    private ItemTypes itemTypes;

    private double quantity;
    private double price;
    private Date created_date;
    private boolean selected;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    public CartItems(int id, int itemId, ItemTypes itemTypes, double quantity, double price, Date created_date, boolean selected, Cart cart, int curry_id) {
        this.id = id;
        this.itemId = itemId;
        this.itemTypes = itemTypes;
        this.quantity = quantity;
        this.price = price;
        this.created_date = created_date;
        this.selected = selected;
        this.cart = cart;
        this.curry_id = curry_id;
    }

    public CartItems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return itemId;
    }

    public void setItem_id(int item_id) {
        this.itemId = item_id;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getCurry_id() {
        return curry_id;
    }

    public void setCurry_id(Integer curry_id) {
        this.curry_id = curry_id;
    }
}
