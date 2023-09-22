package com.travellite2.travellite2.userOrder.model;

import java.util.List;

public class UserOrderRequest {

//    private int userId;
    private String userName;
    private List<ItemRequest> itemRequestList;



//private int bundleOrderId;

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public int getBundleOrderId() {
//        return bundleOrderId;
//    }

//    public void setBundleOrderId(int bundleOrderId) {
//        this.bundleOrderId = bundleOrderId;
//    }

    public String getUserName() { return userName; }

//    public int getUserId() {
//        return userId;
//    }

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public List<ItemRequest> getItemRequestList() {
        return itemRequestList;
    }

    public void setItemRequestList(List<ItemRequest> itemRequestList) {
        this.itemRequestList = itemRequestList;
    }
}


