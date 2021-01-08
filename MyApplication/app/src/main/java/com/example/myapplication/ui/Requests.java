package com.example.myapplication.ui;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class Requests {
    public static int counter=1;
    private int pledgeId;
    private String helper_uid;
    private String pledger_uid;
    private String date;
    private String type;
    private String time;

    public Requests() {
        pledgeId=counter++;
    }

    public String getHelper_uid() { return this.helper_uid; }
    public String getPledger_uid() {
        return this.pledger_uid;
    }
    public String getDate() {
        return this.date;
    }
    public String getType() {
        return this.type;
    }
    public String getTime(){ return this.time;}
    public int getPledgeID(){return this.pledgeId;}


    public void setHelper_uid(String uid){
        this.helper_uid = uid;
    }
    public void setPledger_uid(String uid){
        this.pledger_uid = uid;
    }
    public void setDate(String dt){
        this.date = dt;
    }
    public void setType(String tp){
        this.type = tp;
    }
    public void setTime(String tm){
        this.time = tm;
    }
    @NotNull
    @Override
    public String toString() {
        return date + ", " + time;}
}
