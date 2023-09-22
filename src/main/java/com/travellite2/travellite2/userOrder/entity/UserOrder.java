package com.travellite2.travellite2.userOrder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userOrderId;
    private int userId;
    private int itemId;
    private int itemQuantity;
    private double itemPrice;
    private int bundleOrderId;

    public int getBundleOrderId() {
        return bundleOrderId;
    }

    public void setBundleOrderId(int bundleOrderId) {
        this.bundleOrderId = bundleOrderId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(int userOrderId) {
        this.userOrderId = userOrderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int userOrderQuantity) {
        this.itemQuantity = userOrderQuantity;
    }

}
