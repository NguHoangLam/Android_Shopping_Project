package com.example.project.Entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private int id;
    private int accountId;
    private int productId;
    // 0 = false, 1 = true
    private int isfinish;

    public Cart(int id,int accountId, int productId) {
        this.id =id;
        this.accountId = accountId;
        this.productId = productId;
        this.isfinish = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(int isfinish) {
        this.isfinish = isfinish;
    }
}
