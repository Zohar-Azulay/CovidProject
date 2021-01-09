package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Repo;

import java.io.FileInputStream;

public class HomePageA extends AppCompatActivity {

    FirebaseAuth objectFirebaseAuth;
    TextView userName,Uid,uType;
    Button signOut,delU,reports;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_a);
        FirebaseApp.initializeApp(this);
        objectFirebaseAuth=FirebaseAuth.getInstance();

        uType=findViewById(R.id.user_TypeA);
        signOut=findViewById(R.id.signOutA);
        userName=findViewById(R.id.userNameA);
        delU=findViewById(R.id.deleteUser);
        Uid=findViewById(R.id.UidA);
        reports=findViewById(R.id.pick_reports);

        if(objectFirebaseAuth!=null){
            String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
            userName.setText(currentUser);

            String currentUse=objectFirebaseAuth.getCurrentUser().getUid();
            Uid.setText(currentUse);
            ref=FirebaseDatabase.getInstance().getReference().child("משתמשים").child(currentUse);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String user_Type=snapshot.child("userType").getValue().toString();
                    uType.setText(user_Type);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        signOut.setOnClickListener(v -> {
            objectFirebaseAuth.signOut();
            startActivity(new Intent(HomePageA.this,MainActivity.class));
            finish();
        });
        delU.setOnClickListener(v -> {
            startActivity(new Intent(HomePageA.this,DeleteUsers.class));
            finish();
        });
        reports.setOnClickListener(v -> {
            startActivity(new Intent(HomePageA.this, Reports.class));
            finish();
        });
    }
}