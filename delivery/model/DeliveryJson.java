package com.travellite2.travellite2.delivery.model;

public class DeliveryJson {

    private int userOrderId;
    private String address;
    private String country;
    private String postcode;
    private int bundleOrderId;
    private String userName;

    public int getBundleOrderId() {
        return bundleOrderId;
    }

    public void setBundleOrderId(int bundleOrderId) {
        this.bundleOrderId = bundleOrderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(int userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
