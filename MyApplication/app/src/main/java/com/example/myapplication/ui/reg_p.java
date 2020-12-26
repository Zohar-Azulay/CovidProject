package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class reg_p extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password, phone;
    private Spinner year, city;
    private String TAG = "RegisterUser";
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reg_p);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.fullname_fill_h);
        email = (EditText) findViewById(R.id.email_reg_h);
        password = (EditText) findViewById(R.id.password_reg_h);
        phone = (EditText) findViewById(R.id.cellphone_reg_h);
        city = (Spinner) findViewById(R.id.spinner_address_reg_h);
        year = (Spinner) findViewById(R.id.years_fill_h);

//        Button home = (Button) findViewById(R.id.);
//        home.setOnClickListener(this);          // Back to Home page

        signup = (Button) findViewById(R.id.btn_reg_confirm);
        signup.setOnClickListiner(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar_p);

    }



//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//            case R.id.btn_reg_confirm:
//                reg_confirm();
//                break;
//        }

//        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//
//                        // TODO add to Firestore user table
//                        // TODO change intent to another class
//
//                        Intent intent = new Intent(this, RegisterUser.class);
//                        startActivity(intent);
//                    }
//                }).addOnFailureListener(Throwable::printStackTrace);
//    }



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
            email.setError("כתובת מייל לא תקינה");
            email.requestFocus();
            return true;

        }

        progressBar.setVisibility(View.VISIBLE);
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

                                    if(task.isSuccessful()) {
                                        Toast.makeText(reg_p.this, "ההרשמה הושלמה בהצלחה!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }else {
                                        Toast.makeText(reg_p.this, "ההרשמה נכשלה! נסה שנית!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }



                                }
                            });
                        }else
                            Toast.makeText(reg_p.this,"ההרשמה נכשלה! נסה שנית!",Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {

    }
}


