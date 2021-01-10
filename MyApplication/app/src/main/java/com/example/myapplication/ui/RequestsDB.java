package com.example.myapplication.ui;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class RequestsDB {

    private Date date;
    private String time;
    private String pledgeID;
    private String pleggerUid;
    private boolean status;
    private String type;
    private String startTime;

    public RequestsDB(String type, Date date, String time) {
    }
    public RequestsDB(){}


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPledgeID() {
        return pledgeID;
    }

    public void setPledgeID(String pledgeID) {
        this.pledgeID = pledgeID;
    }

    public String getPleggerUid() {
        return pleggerUid;
    }

    public void setPleggerUid(String pleggerUid) {
        this.pleggerUid = pleggerUid;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartTime(String start){ this.startTime = start;}


    @Override
    public String toString() {
        return  date.getDate() + "/" + date.getMonth() + "/" + date.getYear() + " " + time + "\n" + pledgeID + '\n' + pleggerUid + '\n' + status + '\n' + type;
    }
}
