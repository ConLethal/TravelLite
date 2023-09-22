package com.travellite2.travellite2.userOrder.model;

public class UserOrderSummary {

    private int userOrderId;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private int bundleOrderId;


    public int getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(int userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getBundleOrderId() {
        return bundleOrderId;
    }

    public void setBundleOrderId(int bundleOrderId) {
        this.bundleOrderId = bundleOrderId;
    }
}
