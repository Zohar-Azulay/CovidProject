package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Reports extends AppCompatActivity {

    private ListView report;
    private Button avgBtn;
    private Button typeBtn;

    private DatabaseReference reff;
    private ArrayList<String> typeList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> avgList = new ArrayList<String>();
    private ArrayAdapter<String> adapterAvg;


    private ArrayList<UserDB> copy = new ArrayList<UserDB>();

    private int[] typeCount = {0,0,0,0};
    private int yearSum = 0;
    private int userNum = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reports);

        report = (ListView) findViewById(R.id.reportTextView);
        avgBtn = (Button) findViewById(R.id.ageAvg);
        typeBtn = (Button) findViewById(R.id.typeNum);

        reff =  FirebaseDatabase.getInstance().getReference("משתמשים");
        adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,typeList);
        adapterAvg =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,avgList);


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot ds : snapshot.getChildren()){
                    userNum++;
                    switch (ds.child("userType").getValue(String.class)){
                        case "1": typeCount[0]++;
                            break;
                        case "2": typeCount[1]++;
                            break;
                        case "3": typeCount[2]++;
                            break;
                        default:break;
                    }

                    yearSum += Integer.parseInt(ds.child("birthYear").getValue(String.class));
                }


                //delList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        avgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avgList.clear();
                int avgYear = yearSum / userNum;
                int avgAge = Calendar.getInstance().get(Calendar.YEAR) - avgYear;
                avgList.add("הגיל הממוצע של המשתמשים: " + avgAge);
                report.setAdapter(adapterAvg);
            }
        });

        typeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeList.clear();
                typeList.add("כמות סוגי משתמשים: ");
                typeList.add("מתנדב: " + typeCount[0]);
                typeList.add("מבקש עזרה: " + typeCount[1]);
                typeList.add("מנהל: " + typeCount[2]);

                report.setAdapter(adapter);
            }
        });

    }

}