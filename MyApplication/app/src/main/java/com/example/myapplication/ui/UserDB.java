package com.example.myapplication.ui;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class UserDB {

    private String userID;
    private String name;
    private String city;
    private String birthYear;
    private String email;
    private String phone;
    private String userType;
    public UserDB(){

    }
//    public UserDB(String userID, String name, String city,String birthYear, String email,String phone, String userType){
//        this.userID = userID;
//        this.name = name;
//        this.city = city;
//        this.birthYear = birthYear;
//        this.email = email;
//        this.phone = phone;
//        this.userType = userType;
//
//    }

    public String getUserID(){ return this.userID;    }
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
    public String getUserType(){ return this.userType; }

    public void setUserID(String id){
        userID = id;
    }
    public void setName(String name){ this.name = name; }
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

    @NotNull
    @Override
    public String toString() {
        return name + ", " + city;
        }
}