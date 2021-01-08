package com.example.myapplication.ui;


import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Requests {

    Calendar targetDate = Calendar.getInstance(), openDate = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    private String pledgeID;
    private String helper_uid, pledger_uid;
    private String date, time;
    private String type;
    private boolean status = false; // F for open, T for closed

//    private Date reqYear, reqMonth, reqDay, reqHour, reqMinute;

    public Requests() {

        final String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(openDate.getTime());
        final String currentTime = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(openDate.getTime());
        pledgeID = currentDate + ' ' + currentTime;
    }

    public String getHelper_uid() {
        return this.helper_uid;
    }
    public String getPledger_uid() {
        return this.pledger_uid;
    }
    public String getDateStr() { return this.date; }
    public String getTimeStr(){
        return this.time;
    }
    public String getType() {
        return this.type;
    }
    public String getPledgeID(){ return this.pledgeID; }
    public boolean getStatus() { return this.status; }

    public void setHelper_uid(String uid){ helper_uid = null; }
    public void setPledger_uid(String uid){ pledger_uid = uid; }
    public void setDate(String dt){ date = dt; }
    public void setTime(String tm){
        time = tm;
    }
    public void setType(String tp){
        type = tp;
    }
    public void setStatus(boolean stt) { status = stt; }
}
