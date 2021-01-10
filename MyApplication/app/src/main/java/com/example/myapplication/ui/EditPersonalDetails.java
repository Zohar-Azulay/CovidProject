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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditPersonalDetails extends AppCompatActivity implements View.OnClickListener {

    private Spinner city;
    private EditText phone;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffEditText = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> cityArrayList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private String uid;
    Button btn;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_edit_personal_details);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth != null)
            uid=mAuth.getCurrentUser().getUid();

        city = findViewById(R.id.cityEditSpinner1);
        phone = findViewById(R.id.phoneEdit1);

        showDataCitySpinner();

        btn = findViewById(R.id.editBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityStr = city.getSelectedItem().toString();
                String phoneStr = phone.getText().toString().trim();
                editData(phoneStr, cityStr);
            }
        });
        TextView pass = (TextView) findViewById(R.id.password);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPersonalDetails.this, Container.class).putExtra("id",uid);
                startActivity(intent);
            }
        });
    }

    private void showDataCitySpinner() {
        reffSpinnerCity.child("ערים").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //city
                cityArrayList.clear();
                for (DataSnapshot item : snapshot.getChildren())
                    cityArrayList.add(item.child("עיר").getValue(String.class));


                ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<>(EditPersonalDetails.this, R.layout.fragment_choose, cityArrayList);
                city.setAdapter(arrayAdapterCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void editData(String phoneStr, String cityStr) {

        if (!phoneStr.isEmpty())
            reffEditText.child("משתמשים").child(String.valueOf(uid)).child("phone").setValue(phoneStr);

        if (!cityStr.equals("--בחר עיר--")) {
            reffEditText.child("משתמשים").child(String.valueOf(uid)).child("city").setValue(cityStr);

        }
    }

    @Override
    public void onClick(View v) {

    }
}