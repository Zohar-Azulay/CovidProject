package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.myapplication.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kotlin.text.UStringsKt;


public class openRegV extends Fragment  {

    private String id = "";
    private Button back;
    private ListView openRecList;
    private Spinner sortSpinner;

        private ArrayList<String> spinnerList = new ArrayList<String>();
    private ArrayAdapter<String> spinnerAdapter;

    private DatabaseReference reff;

    private ArrayList<RequestsDB> arrayList = new ArrayList<RequestsDB>();
    private ArrayAdapter<RequestsDB> adapter;

    private RequestsDB req ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_open_reg_v, container, false);

        openRecList = view.findViewById(R.id.openRecList);
//        sortSpinner = view.findViewById(R.id.sort);
//        openRecList.setClickable(true);
//        spinnerList.add("סטטוס");
//        spinnerList.add("תאריך");
//        spinnerList.add("סוג בקשה");
//        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,spinnerList);
//        sortSpinner.setAdapter(spinnerAdapter);

        reff = FirebaseDatabase.getInstance().getReference("Requests");

        adapter = new ArrayAdapter<RequestsDB>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        reff.child("dateStr").setValue(new Date());

//        sortListView("status");
//        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        sortListView("date");
//                        break;
//                    case 1:
//                        sortListView("type");
//                        break;
//                    case 2:
//                        sortListView("status");
//                        break;
//                    default:
//                        sortListView("date");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        openRecList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int index = (int) openRecList.getItemIdAtPosition(position);
//                RequestsDB selectRequests = arrayList.get(index);
//
//            }
//
//        });

        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new homePageV()).addToBackStack("back to home page");
                fr.commit();
            }
        });
        return view;
    }


//    private void sortListView(String sortBy) {
//        arrayList.clear();
//        reff.orderByChild(sortBy).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int year, month, day;
//                String dateStr;
//                Date date;
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    req = new RequestsDB();
//                    dateStr = ds.child("dateStr").getValue(String.class);
//                    if (dateStr != null && dateStr.length() == 10) {
//                        year = Integer.parseInt(dateStr.substring(6, 10));
//                        month = Integer.parseInt(dateStr.substring(3, 5));
//                        day = Integer.parseInt(dateStr.substring(0, 2));
//                        date = new Date(year, month, day);
//                    } else date = Calendar.getInstance().getTime();
//
//                    req.setDate(date);
//                    req.setPledgeID(ds.child("pledgeID").getValue(String.class));
//                    req.setPleggerUid(ds.child("pledger_uid").getValue(String.class));
//                    //req.setStatus(ds.child("status").getValue(boolean.class));
//                    req.setType(ds.child("type").getValue(String.class));
//                    req.setTime(ds.child("timeStr").getValue(String.class));
//                    arrayList.add(req);
//                }
//                openRecList.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

//    public void sortByDate() {
//        ArrayList<RequestsDB> tmp = new ArrayList<RequestsDB>();
//        int tmpIndex = 1;
//        Date dateIndex;
//        for (int index = 0; index < arrayList.size(); index++) {
//            dateIndex = arrayList.get(index).getDate();
//            tmpIndex = 1;
//            while (tmpIndex < tmp.size() && dateIndex.after(tmp.get(tmpIndex).getDate())) {
//                tmpIndex++;
//            }
//            if (tmpIndex == tmp.size())
//                tmp.add(arrayList.get(index));
//            else
//                tmp.add(tmpIndex, arrayList.get(index));
//        }
//        ArrayAdapter<RequestsDB> adapterTmp = new ArrayAdapter<RequestsDB>(getContext(), android.R.layout.simple_list_item_1, tmp);
//        openRecList.setAdapter(adapterTmp);
//
//    }

}
