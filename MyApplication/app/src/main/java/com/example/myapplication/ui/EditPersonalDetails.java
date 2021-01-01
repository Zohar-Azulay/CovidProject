package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditPersonalDetails extends AppCompatActivity implements View.OnClickListener{

    private int id = 0;

    private Spinner city;
    private EditText email, password, phone;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffEditText = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> cityArrayList = new ArrayList<>();

    Button btn;

   protected  void  onCreate(Bundle saveInstanceState) {
       super.onCreate(saveInstanceState);
       setContentView(R.layout.fragment_edit_personal_details);

       if(getIntent().hasExtra("id"))
           id = getIntent().getIntExtra("id",0);

       city = findViewById(R.id.cityEditSpinner1);
       email = findViewById(R.id.emailEdit1);
       password = findViewById(R.id.passwordEdit1);
       phone = findViewById(R.id.phoneEdit1);

       showDataCitySpinner();

       btn = findViewById(R.id.editBtn);

       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cityStr = city.getSelectedItem().toString();
                String phoneStr = phone.getText().toString().trim();
                String passStr = password.getText().toString().trim();
                String emailStr = email.getText().toString().trim();


                if(!wrongInput(phoneStr,passStr,emailStr,cityStr))
                    editData(emailStr, passStr, phoneStr,cityStr);

            }
        });
   }

    private void showDataCitySpinner() {

        reffSpinnerCity.child("ערים").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //city
                cityArrayList.clear();
                for(DataSnapshot item: snapshot.getChildren())
                    cityArrayList.add(item.child("עיר").getValue(String.class));


                ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<>(EditPersonalDetails.this, android.R.layout.simple_spinner_dropdown_item,cityArrayList);
                city.setAdapter(arrayAdapterCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void editData(String emailStr, String passStr, String phoneStr,String cityStr){


        if(!emailStr.isEmpty())
            reffEditText.child("משתמשים").child(String.valueOf(id)).child("email").setValue(emailStr);


        if(!phoneStr.isEmpty())
            reffEditText.child("משתמשים").child(String.valueOf(id)).child("phone").setValue(emailStr);


        if(!passStr.isEmpty())
            reffEditText.child("משתמשים").child(String.valueOf(id)).child("password").setValue(emailStr);

        if(!cityStr.equals("--בחר שנת לידה--")){
            reffEditText.child("משתמשים").child(String.valueOf(id)).child("city").setValue(emailStr);


        }



    }

    private boolean wrongInput(String phoneStr, String passStr, String emailStr, String cityStr){

        boolean flag = false;


        if(passStr.length() < 6 && !passStr.isEmpty()) {
            password.setError("סיסמא חייבת להכיל לפחות 6 תווים");
            password.requestFocus();
            flag = true;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches() && !emailStr.isEmpty()) {
            email.setError("מייל לא תקין");
            email.requestFocus();
            flag = true;
        }

        return flag;

    }


    @Override
    public void onClick(View v) {

    }
}