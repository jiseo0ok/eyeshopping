package com.example.myapplication;

public class ShopList {
     String shopId;
     String shopName;
     String shopAddress;
     String shopHash;
     String shopUrl;
     String shopContext;
     String shopCall;

    public ShopList(String shopId, String shopName, String shopAddress, String shopHash, String shopUrl, String shopContext, String shopCall) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopHash = shopHash;
        this.shopUrl = shopUrl;
        this.shopContext = shopContext;
        this.shopCall = shopCall;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getShopHash() {
        return shopHash;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public String getShopContext() {
        return shopContext;
    }

    public String getShopCall() {
        return shopCall;
    }
}
