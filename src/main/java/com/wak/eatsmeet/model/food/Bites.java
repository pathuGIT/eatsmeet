package com.wak.eatsmeet.model.food;

import jakarta.persistence.*;

@Entity
public class Bites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bite_id")
    private int id;
    private String name;
    private double price;
    private String details;
    private String img_url;

    public Bites(int id, String name, double price, String details, String img_url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.img_url = img_url;
    }

    public Bites() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
