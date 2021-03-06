package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                sendEmail();
            }
        });
    }
    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"zoharazulay31@gmail.com"};
        String[] CC = {"zoharazulay31@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ReportToDevelopers.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}