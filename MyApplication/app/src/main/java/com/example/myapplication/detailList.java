package com.example.myapplication;

public class detailList {
    String contentNo;
    String userID;
    String title;
    String content;
    String shopName;
    String image;
    String time;

    public detailList(String contentNo, String userID, String title, String content, String shopName, String image, String time) {
        this.contentNo = contentNo;
        this.userID = userID;
        this.title = title;
        this.content = content;
        this.shopName = shopName;
        this.image = image;
        this.time = time;
    }



    public String getContentNo() {
        return contentNo;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getShopName() {
        return shopName;
    }

    public String getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }
}