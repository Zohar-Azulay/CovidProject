package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class pledges_list extends Fragment {

    private final String TAG=" pledges_list";
    private FirebaseAuth mAuth;
    private DatabaseReference userDB;
    private FirebaseDatabase reffPledges;
    private String currentUserUID;
    //    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private ListView listView;

//    private ArrayList<String> importedList = new ArrayList<>();
    private ArrayAdapter<Requests> PlgListAdapter;

    ArrayList<Requests>  pledgedList = new ArrayList<>();

    public pledges_list() {
        // Empty constractor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pledgeListView = inflater.inflate(R.layout.fragment_pledges_list, container, false);
        Toast.makeText(getContext(), "Pledges List- 1", Toast.LENGTH_SHORT).show();

//        Log.d(TAG, "OnCreateView: Started");
        ImageButton btnSupport = pledgeListView.findViewById(R.id.btn_support);
        Button btn_openPledge = pledgeListView.findViewById(R.id.btn_pledges_list_continue);

        mAuth = FirebaseAuth.getInstance();
        currentUserUID = mAuth.getCurrentUser().getUid();
        userDB = FirebaseDatabase.getInstance().getReference().child("Requests");
        Toast.makeText(getContext(), "Pledges List- 2", Toast.LENGTH_SHORT).show();
        userDB.child(currentUserUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    boolean isPledged = (boolean) postSnapshot.getValue();
                    if(isPledged)
//                        pledgedList.add(postSnapshot.child(""));
                        Toast.makeText(getContext(), "Pledges List- 3", Toast.LENGTH_SHORT).show();

                    pledgedList.add((Requests) postSnapshot.getValue()); // Casted
//                        pledgedList.add(postSnapshot.getKey());           // Origin
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {  }  });
        PlgListAdapter = new PlgListAdapter(this.getContext(),  R.layout.adapter_listview_layout, pledgedList);
        listView.setAdapter(PlgListAdapter);

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

        return pledgeListView;
    }



}