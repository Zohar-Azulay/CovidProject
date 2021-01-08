package com.example.myapplication.ui;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class pledges_list extends Fragment {

    private final String TAG=" pledges_list";
    private DatabaseReference userAuth;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    ArrayList<ClipData.Item>  pledgedList = new ArrayList<ClipData.Item>();

    public pledges_list() {
        // Empty constractor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pledges_list, container, false);
        Log.d(TAG, "OnCreate: Started");
        ImageButton btnSupport = view.findViewById(R.id.btn_support);
        Button btn_openPledge = view.findViewById(R.id.btn_pledges_list_continue);


        arrayAdapter = new ArrayAdapter<String>(this, R.layout.adapter_listview_layout, );
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr_support = getFragmentManager().beginTransaction();
                fr_support.replace(R.id.fragment_pledger_container, new fragment_support()).addToBackStack("switch to support fragment");
                fr_support.commit();
            }
        });
//
//        btn_openPledge.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                assert getFragmentManager() != null;
//                FragmentTransaction fr_open = getFragmentManager().beginTransaction();
//                fr_open.replace(R.id.fragment_pledger_container, new open_pledge()).addToBackStack("switch to open pledge menu");
//            }
//        });




//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_pledges_list);
//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("");
//    }

        return view;
    }



}