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
import android.widget.EditText;

import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

public class   ReportToDevelopers extends AppCompatActivity {
    EditText et_to, etSubject, etMessage;
    Button bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_report_to_developers);
        FirebaseApp.initializeApp(this);


        et_to = findViewById(R.id.et_to);
        etSubject = findViewById(R.id.et_toSubject);
        etMessage = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_send);


        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+ et_to.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                startActivity(intent);

            }
        });
    }

}