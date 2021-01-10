package com.example.myapplication.ui;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class RequestsDB {

    private Date date;
    private String time;
    private String pledgeID;
    private String pleggerUid;
    private String helper_uid;
    private boolean status;
    private String type;


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

    public String getHelper_uid() {
        return helper_uid;
    }

    public void setHelper_uid(String helper_uid) {
        this.helper_uid =helper_uid;
    }

    @Override
    public String toString() {
        return  DateFormat.getDateInstance(DateFormat.SHORT).format(date) + " " + time + "\n" + pledgeID + '\n' + pleggerUid + '\n' + status + '\n' + type;
    }

}