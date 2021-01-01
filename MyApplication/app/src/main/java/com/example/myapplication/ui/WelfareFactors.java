package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

class WelfareFactorsextends extends AppCompatActivity {

    private int id = 0;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_welfare_factors);

        if(getIntent().hasExtra("id"))
            id = getIntent().getIntExtra("id",0);
    }
}