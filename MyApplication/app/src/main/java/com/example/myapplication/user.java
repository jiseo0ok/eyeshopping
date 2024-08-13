package com.example.myapplication;

public class user {

    String userID;
    String userPassword;
    String userName;
    String userNickname;
    String Grade;

    public user(String userID, String userPassword, String userName, String userNickname, String grade) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
        Grade = grade;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getGrade() {
        return Grade;
    }
}
