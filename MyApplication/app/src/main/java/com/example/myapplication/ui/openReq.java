package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class openReq extends AppCompatActivity {

    private String id = "";
    private Button back;
    private ListView openRecList;
    private Spinner sortSpinner;

    private ArrayList<String> spinnerList = new ArrayList<String>();
    private ArrayAdapter<String> spinnerAdapter;

    private DatabaseReference reff;

    private ArrayList<RequestsDB> arrayList = new ArrayList<RequestsDB>();
    private ArrayList<RequestsDB> copy = new ArrayList<RequestsDB>();
    private ArrayAdapter<RequestsDB> adapter;

    private RequestsDB req = new RequestsDB();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_open_req);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);

        openRecList = (ListView) findViewById(R.id.openRecList);
        sortSpinner = (Spinner) findViewById(R.id.sort);

        spinnerList.add("בחר");
        spinnerList.add("סטטוס");
        spinnerList.add("תאריך");
        spinnerList.add("סוג בקשה");
        spinnerAdapter = new ArrayAdapter<>(openReq.this, android.R.layout.simple_spinner_dropdown_item,spinnerList);
        sortSpinner.setAdapter(spinnerAdapter);

        reff =  FirebaseDatabase.getInstance().getReference("Requests");
        adapter =  new ArrayAdapter<RequestsDB>(this,android.R.layout.simple_list_item_1,arrayList);


        sortListView("status");
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        sortListView("status");
                        break;
                    case 2: sortListView("dateStr");
                        break;
                    case 3: sortListView("type");
                        break;
                    default:break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(openReq.this, HomeAdmin.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void sortListView(String sortBy){
        arrayList.clear();

        reff.orderByChild(sortBy).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int year, month, day;
                String dateStr;
                Date date;
                for(DataSnapshot ds : snapshot.getChildren()){
                    req = new RequestsDB();
                    dateStr = ds.child("date").getValue(String.class);
                     if (dateStr != null && dateStr.length() == 10) {
                        year = Integer.parseInt(dateStr.substring(6,10));
                        month = Integer.parseInt(dateStr.substring(3,5));
                        day = Integer.parseInt(dateStr.substring(0,2));
                        date = new Date(year, month, day);
                    }
                    else date = Calendar.getInstance().getTime();

                    req.setDate(date);
                    req.setPledgeID(ds.child("pledgeID").getValue(String.class));
                    req.setPleggerUid(ds.child("pledger_uid").getValue(String.class));
                    if(ds.child("status") != null)
                        req.setStatus(ds.child("status").getValue(boolean.class));
                    req.setType(ds.child("type").getValue(String.class));
                    req.setTime(ds.child("time").getValue(String.class));
                    arrayList.add(req);
                    copy.add(req);
                }
                openRecList.setAdapter(adapter);
                if(sortBy.equals("date"))
                    sortByDate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void sortByDate(){
        ArrayList<RequestsDB> tmp = new ArrayList<RequestsDB>();
        if(copy.size() == 1)
            tmp.add(copy.get(0));
        else {
            int size = 0;
            while (copy.size() != 0) {
                while (size < tmp.size() && copy.get(0).getDate().after(tmp.get(size).getDate()))
                    size++;
                if (size == tmp.size())
                    tmp.add(copy.get(0));
                else tmp.add(size, copy.get(0));
                copy.remove(0);
            }
        }
        ArrayAdapter<RequestsDB> adapterTmp = new ArrayAdapter<RequestsDB>(this,android.R.layout.simple_list_item_1,tmp);
        openRecList.setAdapter(adapterTmp);

    }
}