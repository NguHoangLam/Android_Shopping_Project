package com.example.project.Entity;

import java.io.Serializable;

public class Product implements Serializable {

    public  static  String TYPE_GUNDAM = "GUNDAM";
    public  static  String TYPE_GAME = "GAME";
    public  static  String TYPE_ANIME = "ANIME";
    public  static  String TYPE_POKEMON = "POKEMON";

    private int id; //0
    private int image;//1
    private String name;//2
    private int price;//3
    private String type;//4
    private String description;//5
    private int quantity;//6

    public Product(int id, int image, String name, int price, String type,String description,int quantity) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
    }

    public Product(int image, String name, int price, String type,String description,int quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
