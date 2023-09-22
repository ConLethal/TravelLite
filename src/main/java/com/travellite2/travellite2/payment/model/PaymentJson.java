package com.travellite2.travellite2.payment.model;

public class PaymentJson {

    private String longNumber;
    private String nameOnCard;
    private String expiry;
    private String securityCode;
    private double paid;
    private int bundleOrderId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getBundleOrderId() {
        return bundleOrderId;
    }

    public void setBundleOrderId(int bundleOrderId) {
        this.bundleOrderId = bundleOrderId;
    }




    public String getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(String longNumber) {
        this.longNumber = longNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }
}
