//package com.example.myapplication.ui;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myapplication.R;
//
//
//public class open_pledge extends AppCompatActivity {
//
//    private String currentPlg;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_open_pledge);
//
//            currentPlg = getIntent().getExtras().get("ClickedValue").toString();
//        Toast.makeText(open_pledge.this, currentPlg, Toast.LENGTH_SHORT).show();
//    }
//
//}