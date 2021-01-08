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
    EditText etSubject, etMessage;
    Button bt_send;
    String et_to = "zoharazulay31@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_report_to_developers);
        FirebaseApp.initializeApp(this);


        etSubject = (EditText) findViewById(R.id.et_toSubject);
        etMessage = (EditText) findViewById(R.id.et_message);
        bt_send = (Button) findViewById(R.id.bt_send);


        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+ et_to));
                //Intent intent = new Intent(Intent.ACTION_SEND);
                //intent.putExtra(intent.EXTRA_EMAIL,  new String []{"zoharazulay31@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,etMessage.getText().toString());
                //intent.setSelector(new Intent(Intent.ACTION_SENDTO));
                startActivity(intent);
            }
        });
    }

}