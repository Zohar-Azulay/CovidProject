package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        List<dataRequsts> listDataR;

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,new LoginV2());
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }


}