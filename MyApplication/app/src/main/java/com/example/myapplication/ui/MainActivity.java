package com.example.myapplication.ui;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> main
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
=======
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
>>>>>>> Zohar
>>>>>>> main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

<<<<<<< HEAD
    }
=======
<<<<<<< HEAD
    }
=======
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container,new FirstPage());
        fragmentTransaction.commit();
    }

>>>>>>> Zohar
>>>>>>> main
}