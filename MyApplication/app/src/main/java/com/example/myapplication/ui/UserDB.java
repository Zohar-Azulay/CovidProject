package com.example.myapplication.ui;

import android.util.Log;

public class UserDB {

    static int userID = 1;
    private String name;
    private String city;
    private String birthYear;
    private String email;
    private String phone;
    private String userType;
    private String password;

    public UserDB(){
        userID++;
    }
/*
    public UserDB(String userID, String name, String city, int birthYear, String email,String phone, String userType,String password){
        this.userID = userID;
        this.name = name;
        this.city = city;
        this.birthYear = birthYear;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.password = password;
    }

    public UserDB(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }*/

    public int getUserID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String getBirthYear(){
        return this.birthYear;
    }

    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getUserType(){
        return this.userType;
    }
    public String getPassword() {
        return password;
    }


    public void setName(String name){
       this.name = name;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setBirthYear(String birthYear){
        this.birthYear = birthYear;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setUserType(String userType){
        this.userType = userType;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", birthYear=" + birthYear +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", userType='" + userType + '\'' +
                '}';
    }
}
