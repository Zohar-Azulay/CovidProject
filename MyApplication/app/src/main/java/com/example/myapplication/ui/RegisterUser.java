package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password, phone;
    private Spinner year, city;
    private String TAG = "RegisterUser";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reg_a);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.nameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        city = (Spinner) findViewById(R.id.citySpinner);
        year = (Spinner) findViewById(R.id.yearSpinner);

        Button singup = (Button) findViewById(R.id.btn1);
        singup.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        // TODO add to Firestore user table
                        // TODO change intent to another class

                        Intent intent = new Intent(this, RegisterUser.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(Throwable::printStackTrace);
    }



    private boolean register(){
        String nameStr = name.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();
        String passStr = password.getText().toString().trim();
        String emailStr = email.getText().toString().trim();


        if(isEmpty(nameStr,name) || isEmpty(phoneStr,phone) || isEmpty(passStr,password))
            return true;


        if(passStr.length() < 6) {
            password.setError("סיסמא חייבת להכיל לפחות 6 תווים");
            password.requestFocus();
            return true;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("מייל לא תקין");
            email.requestFocus();
            return true;
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
                                        Toast.makeText(RegisterUser.this,"user has been register successfuly!",Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(RegisterUser.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();



                                }
                            });
                        }else
                            Toast.makeText(RegisterUser.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                    }
                });
        return false;

    }

    private boolean isEmpty(String str,EditText v){
        if(str.isEmpty()){
            v.setError("שדה חובה");
            v.requestFocus();
            return true;
        }
        return false;
    }
}
