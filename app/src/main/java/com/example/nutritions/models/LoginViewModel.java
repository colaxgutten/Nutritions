package com.example.nutritions.models;

public class LoginViewModel {
    private String userName;
    private String password;

    public LoginViewModel(){
        userName = "";
        password = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
