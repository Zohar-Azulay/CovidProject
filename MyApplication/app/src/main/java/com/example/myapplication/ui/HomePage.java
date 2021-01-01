package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    FirebaseAuth objectFirebaseAuth;
    TextView userName,Uid;
    Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        objectFirebaseAuth=FirebaseAuth.getInstance();
        signOut=findViewById(R.id.signOut);
        userName=findViewById(R.id.userName);
        Uid=findViewById(R.id.Uid);
        if(objectFirebaseAuth!=null){
            String currentUser=objectFirebaseAuth.getCurrentUser().getEmail();
            userName.setText(currentUser);
            String currentUse=objectFirebaseAuth.getCurrentUser().getUid();
            Uid.setText(currentUse);
        }
        signOut.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                objectFirebaseAuth.signOut();
                startActivity(new Intent(HomePage.this,MainActivity.class));

                finish();
            }
        });
    }
}