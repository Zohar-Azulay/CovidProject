package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import kotlin.text.UStringsKt;


public class pledges_list extends AppCompatActivity {

    private final String TAG=" pledges_list";
    private FirebaseAuth mAuth;
    private DatabaseReference userDB;
    private FirebaseDatabase reffPledges;
    private String currentUserUID;

    private ArrayList<RequestsDB>  pledgedList = new ArrayList<RequestsDB>();
    private ArrayAdapter<RequestsDB> PlgListAdapter;

    public pledges_list() {
        // Empty constractor
    }

    private String id = "";
    private Button btn_openPledge;
    private ListView openReqList;
    private DatabaseReference reff;

    private ArrayList<RequestsDB> arrayList = new ArrayList<RequestsDB>();
    private ArrayAdapter<RequestsDB> adapter;

    private RequestsDB req = new RequestsDB();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pledges_list);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);

        openReqList = (ListView) findViewById(R.id.pledges_list);
        reff =  FirebaseDatabase.getInstance().getReference("Requests");
        adapter =  new ArrayAdapter<RequestsDB>(this,android.R.layout.simple_list_item_1,arrayList);
        sortListView();
    }

    private void sortListView(){
        arrayList.clear();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int year, month, day;
                String dateStr;
                Date date;
                mAuth = FirebaseAuth.getInstance();
                currentUserUID = mAuth.getCurrentUser().getUid();
                for(DataSnapshot ds : snapshot.getChildren()){
                    String temp = ds.child("pledger_uid").getValue(String.class);
                    if (ds.child("pledger_uid").getValue(String.class).equals(currentUserUID)) {
                        req = new RequestsDB();
                        dateStr = ds.child("date").getValue(String.class);
                        if (dateStr != null && dateStr.length() == 10) {
                            year = Integer.parseInt(dateStr.substring(6, 10));
                            month = Integer.parseInt(dateStr.substring(3, 5));
                            day = Integer.parseInt(dateStr.substring(0, 2));
                            date = new Date(year, month, day);
                        } else date = Calendar.getInstance().getTime();
                        req.setDate(date);
                        req.setStartTime(ds.child("pledgeID").getValue(String.class));
                        req.setStatus(ds.child("status").getValue(boolean.class));
                        req.setType(ds.child("type").getValue(String.class));
                        req.setTime(ds.child("time").getValue(String.class));
                        arrayList.add(req);
                   }
                }
                openReqList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}