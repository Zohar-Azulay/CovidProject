package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity  {

    //private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

//
        //btn = (Button) findViewById(R.id.startBtn);
        //btn.setOnClickListener(new View.OnClickListener() {

        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);


/*
        private EditText name, email, password, phone;
        private Spinner year, city;

        private FirebaseAuth mAuth;

        protected void onClick(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mAuth = FirebaseAuth.getInstance();

            name = (EditText) findViewById(R.id.nameInput);
            email = (EditText) findViewById(R.id.emailInput);
            password = (EditText) findViewById(R.id.passwordInput);
            phone = (EditText) findViewById(R.id.phoneInput);
            city = (Spinner) findViewById(R.id.citySpinner);
            year = (Spinner) findViewById(R.id.yearSpinner);

        }


        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.btn1) {
                register();
                startActivity(new Intent(this, MainActivity.class)); //go to main page
            }
        }


        private void register(){
            String nameStr = name.getText().toString().trim();
            String phoneStr = phone.getText().toString().trim();
            String passStr = password.getText().toString().trim();
            String emailStr = email.getText().toString().trim();


            if(isEmpty(nameStr,name) || isEmpty(phoneStr,phone) || isEmpty(passStr,password))
                return;


            if(passStr.length() < 6) {
                password.setError("סיסמא חייבת להכיל לפחות 6 תווים");
                password.requestFocus();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                email.setError("מייל לא תקין");
                email.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                UserDB user = new UserDB(nameStr,emailStr,phoneStr,passStr);

                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful())
                                            Toast.makeText(MainActivity.this,"user has been register successfuly!",Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(MainActivity.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();



                                    }
                                });
                            }else
                                Toast.makeText(MainActivity.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                        }
                    });


        }
        private boolean isEmpty(String str,EditText v){
            if(str.isEmpty()){
                v.setError("שדה חובה");
                v.requestFocus();
                return true;
            }
            return false;
        }
*/
    }
}