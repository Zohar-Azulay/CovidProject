package com.example.myapplication.ui;

import android.util.Log;

public class UserDB {

    private String userID;
    private String name;
    private String city;
    private int birthYear;
    private String email;
    private String phone;
    private String userType;
    private String password;

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
    }

    public String getUserID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public int getBirthYear(){
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

    public void setUserID(String id){
        userID = id;
    }
    public void setName(String name){
       this.name = name;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setBirthYear(int birthYear){
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
