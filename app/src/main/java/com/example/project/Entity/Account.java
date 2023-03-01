package com.example.project.Entity;

import androidx.cardview.widget.CardView;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String username;
    private String phone;
    private String password;
    private String createDate;

    public Account(int id, String username, String phone, String password,String createDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.createDate = createDate;
    }
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
