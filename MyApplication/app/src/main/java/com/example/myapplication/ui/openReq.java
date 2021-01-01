package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

public class openReq extends AppCompatActivity {

    private int id = 0;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_open_req);

        if(getIntent().hasExtra("id"))
            id = getIntent().getIntExtra("id",0);

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
}