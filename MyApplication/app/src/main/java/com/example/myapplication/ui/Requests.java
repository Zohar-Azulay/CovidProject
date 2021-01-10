package com.example.myapplication.ui;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Requests {

    Calendar targetDate = Calendar.getInstance(), openDate = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    private String pledgeID;
    private String helper_uid, pledger_uid;
    private Date date;
    private String time;
    private String type;
    private String startTime;
    private boolean status = false; // F for open, T for closed

    final String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(openDate.getTime());
    final String currentTime = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(openDate.getTime());

//    private Date reqYear, reqMonth, reqDay, reqHour, reqMinute;

    public Requests() {
        pledgeID = currentDate + ' ' + currentTime;
    }

    public Requests(String type, Date date, String time){
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public String getHelper_uid() {
        return this.helper_uid;
    }
    public String getPledger_uid() {
        return this.pledger_uid;
    }
    public Date getDate() { return this.date; }
    public String getTime(){
        return this.time;
    }
    public String getType() {
        return this.type;
    }
    public String getPledgeID(){ return this.pledgeID; }
    public String getStartTime(){ return this.startTime; }
    public boolean getStatus() { return this.status; }

    public void setHelper_uid(String uid){ helper_uid = null; }
    public void setPledger_uid(String uid){ pledger_uid = uid; }
    public void setDate(Date dt){ date = dt; }
    public void setTime(String tm){
        time = tm;
    }
    public void setType(String tp){
        type = tp;
    }
    public void setStartTime(String start){ this.startTime = start;}
    public void setStatus(boolean stt) { status = stt; }

    @Override
    public String toString() {
        return  date.getDate() + "/" +
                date.getMonth() + "/" +
                date.getYear() + " " +
                time + "\n" + pledgeID + '\n' + status + '\n' + type;

//        return "Requests{" +
//                ", date=" + SimpleDateFormat.getDateInstance(applyPattern(dateFormat)).format(date) +
//                ", time='" + time + '\'' +
//                ", type='" + type + '\'' +
//                '}';
    }
}
