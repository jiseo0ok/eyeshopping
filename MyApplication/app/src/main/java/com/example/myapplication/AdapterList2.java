package com.example.myapplication;

public class AdapterList2 {
    String No,userID,hash,content,image,date;

    public String getNo() {
        return No;
    }

    public String getUserID() {
        return userID;
    }

    public String getHash() {
        return hash;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }



    public String getDate() {
        return date;
    }

    public AdapterList2(String no, String userID, String hash, String content, String image, String date) {
        No = no;
        this.userID = userID;
        this.hash = hash;
        this.content = content;
        this.image = image;

        this.date = date;
    }
}

