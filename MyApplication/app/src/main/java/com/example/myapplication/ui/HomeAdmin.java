package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.ref.WeakReference;

public class HomeAdmin extends AppCompatActivity implements View.OnClickListener {

    private String id = "LYCkp9YAw4StPahWyZncXg3b6R63";
    private Button openReq, rtd, msg, edit, reports,deleteUser, logOut, welfare;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_admin);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);
        mAuth=FirebaseAuth.getInstance();
        openReq = (Button) findViewById(R.id.openReq);
        rtd = (Button) findViewById(R.id.rtd);
        msg = (Button) findViewById(R.id.msg);
        edit = (Button) findViewById(R.id.edit);
        reports = (Button) findViewById(R.id.reports);
        deleteUser = (Button) findViewById(R.id.deleteUser);
        logOut = (Button) findViewById(R.id.logOut);
        welfare = (Button) findViewById(R.id.welfare);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivity(EditPersonalDetails.class);
            }
        });

        rtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ReportToDevelopers.class);
            }
        });

        openReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(openReq.class);
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(DeleteUsers.class);
            }
        });

        welfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(WelfareFactors.class);
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(Reports.class);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://console.firebase.google.com/u/0/project/cov-aid-db7dc/notification/compose"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(HomeAdmin.this,MainActivity.class));
                finish();
            }
        });
    }

    public void openActivity(Class activityClass){
        Intent intent = new Intent(HomeAdmin.this, activityClass);
        intent.putExtra("id",id);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }
}