package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class DeleteUsers extends AppCompatActivity {

    private int id = 0;

    private UserDB user = new UserDB();

    //private Button delBtn;
    private TextView delText;
    private ListView delList;

    private DatabaseReference reff;

    private ArrayList<UserDB> arrayList = new ArrayList<UserDB>();
    private ArrayAdapter<UserDB> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delete_users);

        if(getIntent().hasExtra("id"))
            id = getIntent().getIntExtra("id",0);

        //delBtn = (Button) findViewById(R.id.deleteBtn);
        delText = (TextView) findViewById(R.id.deleteText);
        delList = (ListView) findViewById(R.id.delList);

        reff =  FirebaseDatabase.getInstance().getReference("משתמשים");
        adapter =  new ArrayAdapter<UserDB>(this, R.layout.fragment_delete_users,R.id.deleteText,arrayList);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    user = ds.getValue(UserDB.class);
                    arrayList.add(user);
                }
                delList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








//        delList.setAdapter(adapter);
/*
        reff.child("משתמשים").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String str = snapshot.getValue(String.class);
                arrayList.add(str);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if(arrayList.isEmpty())
        delText.setText("empty");

        else delText.setText("full");
*/

    }
}