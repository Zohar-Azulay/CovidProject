package com.example.myapplication.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password, phone;
    private String TAG = "RegisterUser";
    private CheckBox terms;

    private DatabaseReference reff;
    private UserDB userObj;

    //SPINNERS
    private Spinner year, city;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffSpinnerYear = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> yearArrayList = new ArrayList<>();
    private ArrayList<String> cityArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reg_a);

        name = (EditText) findViewById(R.id.nameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        city = (Spinner) findViewById(R.id.citySpinner);
        year = (Spinner) findViewById(R.id.yearSpinner);
        terms = (CheckBox) findViewById(R.id.termsCheck1);

        Button singup = (Button) findViewById(R.id.btn1);
        singup.setOnClickListener(this);

        userObj = new UserDB();
        reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");

        showDataCitySpinner();
        showDataYearSpinner();

    }

    private void showDataYearSpinner() {
        reffSpinnerYear.child("שנת לידה").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //year
                yearArrayList.clear();
                for(DataSnapshot item: snapshot.getChildren())
                    yearArrayList.add(item.child("שנה").getValue(String.class));
                //אם קורס - לוודא שיש גרשיים בתאריכים

                ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<>(RegisterUser.this, android.R.layout.simple_spinner_dropdown_item,yearArrayList);
                year.setAdapter(arrayAdapterYear);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
    }

    private void showDataCitySpinner() {
        reffSpinnerCity.child("ערים").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //city
                cityArrayList.clear();
                for(DataSnapshot item: snapshot.getChildren())
                    cityArrayList.add(item.child("עיר").getValue(String.class));

                ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<>(RegisterUser.this, android.R.layout.simple_spinner_dropdown_item,cityArrayList);
                city.setAdapter(arrayAdapterCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public void onClick(View v) {

        String nameStr = name.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();
        String passStr = password.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String cityStr = city.getSelectedItem().toString();
        String yearStr = year.getSelectedItem().toString();


        if(wrongInput(nameStr,phoneStr,passStr,emailStr,cityStr,yearStr))
            Toast.makeText(RegisterUser.this,"אחד השדות לא תקינים,נסה שוב!",Toast.LENGTH_LONG).show();
        else if(!terms.isChecked())
            Toast.makeText(RegisterUser.this,"יש לאשר את תנאי השימוש!",Toast.LENGTH_LONG).show();
     //   else if(userExist(nameStr, phoneStr, yearStr))
     //       Toast.makeText(RegisterUser.this,"המשתמש קיים במערכת!",Toast.LENGTH_LONG).show();
        else {
            userObj.setName(nameStr);
            userObj.setPhone(phoneStr);
            userObj.setPassword(passStr);
            userObj.setEmail(emailStr);
            userObj.setCity(cityStr);
            userObj.setBirthYear(yearStr);

            reff.child(userObj.getUserID()).setValue(userObj);

            Intent intent = new Intent(this, EditPersonalDetails.class);
            startActivity(intent);

        }

    }

    // שם טלפון שנת לידה
    private void userExist(String nameStr, String phoneStr, String yearStr) {
        reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dbName, dbPhone, dbYear;
                for (DataSnapshot item : snapshot.getChildren()) {
                    dbName = item.child("name").getValue(String.class);
                    if(nameStr.equals(dbName)){
                        dbPhone = item.child("phone").getValue(String.class);
                        if(phoneStr.equals(dbPhone)){
                            dbYear = item.child("birthYear").getValue(String.class);
                            if(yearStr.equals(dbYear)) {
                                Log.d("MSG","user exists");
                            }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private boolean wrongInput(String nameStr, String phoneStr, String passStr, String emailStr, String cityStr, String yearStr) {
        boolean flag = false;

        if (cityStr.equals("--בחר עיר--")) {
            name.requestFocus();
            flag = true;
        }
        if (yearStr.equals("--בחר שנת לידה--")) {
            name.setError("שדה חובה");
            name.requestFocus();
            flag = true;
        }
        if (nameStr.isEmpty()) {
            name.setError("שדה חובה");
            name.requestFocus();
            flag = true;
        }
        if (phoneStr.isEmpty()) {
            phone.setError("שדה חובה");
            phone.requestFocus();
            flag = true;
        }
        if (passStr.isEmpty()) {
            password.setError("שדה חובה");
            password.requestFocus();
            flag = true;
        }

        if (passStr.length() < 6) {
            password.setError("סיסמא חייבת להכיל לפחות 6 תווים");
            password.requestFocus();
            flag = true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("מייל לא תקין");
            email.requestFocus();
            flag = true;
        }

        return flag;

    }
}
