package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.common.annotations.GwtCompatible;
import com.google.firebase.FirebaseApp;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, new LoginV2());
        fragmentTransaction.commit();

        }


    @Override
    public void onBackPressed(){
        Toast.makeText(getBaseContext(), "אין יציאה", Toast.LENGTH_LONG).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.mymenu,);
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//
//            case R.id.itm_increase:
//
//                break;
//            case R.id.itm_decrease:
//
//            break;
//            case R.id.itm_profile:
//
//            break;
//            case R.id.itm_exit:
//                finishAffinity();
//            break;
//        }
//
//      return super.onOptionsItemSelected(item);
//   }
}
