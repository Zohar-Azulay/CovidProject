package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);


        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,new homePageV());
        fragmentTransaction.commit();
    }


}