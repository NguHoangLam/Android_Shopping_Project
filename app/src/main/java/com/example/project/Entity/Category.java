package com.example.project.Entity;



public class Category {
    private  int image;
    private String content;
    private String color;

    public Category(int image, String content, String color) {
        this.image = image;
        this.content = content;
        this.color =color;
    }

    public String getColor() {
        return color;
    }

    public int getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }
}
