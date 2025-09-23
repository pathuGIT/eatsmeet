package com.wak.eatsmeet.model.food;

import jakarta.persistence.*;

import java.util.Date;

@Entity
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

    public Snacks(int id, String name, double stock, double price, boolean scale, String description, Date mnf_date, Date exp_date, String brand, String img_url) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.scale = scale;
        this.description = description;
        this.mnf_date = mnf_date;
        this.exp_date = exp_date;
        this.brand = brand;
        this.img_url = img_url;
    }

    public Snacks() {
    }

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

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isScale() {
        return scale;
    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMnf_date() {
        return mnf_date;
    }

    public void setMnf_date(Date mnf_date) {
        this.mnf_date = mnf_date;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
