package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class openReqV extends AppCompatActivity {

    private String id = "",uid;
    private Button back;
    private ListView openRecList;
    private Spinner sortSpinner;

    private ArrayList<String> spinnerList = new ArrayList<String>();
    private ArrayAdapter<String> spinnerAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference reff;

    private ArrayList<RequestsDB> arrayList = new ArrayList<RequestsDB>();
    private ArrayAdapter<RequestsDB> adapter;
//    private ListView delList;

    private RequestsDB req = new RequestsDB();
    private String selected;
//    private String selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_req);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);
        mAuth=FirebaseAuth.getInstance();
        openRecList = (ListView) findViewById(R.id.openRecList);
        sortSpinner = (Spinner) findViewById(R.id.sort);

        spinnerList.add("בחר");
        spinnerList.add("סטטוס");
        spinnerList.add("תאריך");
        spinnerList.add("סוג בקשה");
        spinnerAdapter = new ArrayAdapter<>(openReqV.this, android.R.layout.simple_spinner_dropdown_item,spinnerList);
        sortSpinner.setAdapter(spinnerAdapter);

        reff =  FirebaseDatabase.getInstance().getReference("Requests");
        adapter =  new ArrayAdapter<RequestsDB>(this,android.R.layout.simple_list_item_1,arrayList);


//        reff.child("dateStr").setValue(new Date());
        sortListView("status");
        openRecList.setClickable(true);
        openRecList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index =(int) openRecList.getItemIdAtPosition(position);
                RequestsDB select = arrayList.get(index);
                selected=select.getPledgeID();
                uid=mAuth.getCurrentUser().getUid();
                select.setHelper_uid(uid);

            }
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        sortListView("status");
                        break;
                    case 2: //sortListView("date");
                        break;
                    case 3: sortListView("type");
                        break;
                    default://sortListView("date");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





//        back = (Button) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(openReq.this, homePageV.class);
//                intent.putExtra("id",id);
//                startActivity(intent);
//            }
//        });
    }

    private void sortListView(String sortBy){
        arrayList.clear();
        reff.addValueEventListener(new ValueEventListener() {
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
                    req.setTime(ds.child("timeStr").getValue(String.class));
                    arrayList.add(req);
                }
                openRecList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
/*
    public void sortByDate(){
        ArrayList<RequestsDB> tmp = new ArrayList<RequestsDB>();
        int tmpIndex = 1;
        Date dateIndex;
        for(int index = 0; index < arrayList.size(); index++) {
            dateIndex = arrayList.get(index).getDate();
            tmpIndex = 1;
            while (tmpIndex < tmp.size() && dateIndex.after(tmp.get(tmpIndex).getDate())) {
                tmpIndex++;
            }
            if (tmpIndex == tmp.size())
                tmp.add(arrayList.get(index));
            else
                tmp.add(tmpIndex, arrayList.get(index));
        }
        ArrayAdapter<RequestsDB> adapterTmp = new ArrayAdapter<RequestsDB>(this,android.R.layout.simple_list_item_1,tmp);
        openRecList.setAdapter(adapterTmp);
    }*/
}