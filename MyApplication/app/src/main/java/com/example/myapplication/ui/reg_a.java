package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reg_a extends Fragment {
    private EditText name, email, phone ,password;
    private Button signUpBtnA;
    private CheckBox terms;
    private View objectRegFragment;
    private FirebaseAuth mAuth;
    private DatabaseReference reff;
    private UserDB userObj;
    private String uid;
    private final String TAG = "reg_a";


    //SPINNERS
    private Spinner year, city;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffSpinnerYear = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> yearArrayList = new ArrayList<>();
    private ArrayList<String> cityArrayList = new ArrayList<>();

    public reg_a(){
        //empty
    }

    private  void initializeA(){
        try{
            mAuth= FirebaseAuth.getInstance();
            name=objectRegFragment.findViewById(R.id.reg_a_name);
            email=objectRegFragment.findViewById(R.id.reg_a_email);
            phone=objectRegFragment.findViewById(R.id.reg_a_phone);
            password=objectRegFragment.findViewById(R.id.reg_a_password);
            signUpBtnA=objectRegFragment.findViewById(R.id.reg_a_finish);
            terms=objectRegFragment.findViewById(R.id.reg_a_takanon);
            city =objectRegFragment.findViewById(R.id.reg_a_city);
            year = objectRegFragment.findViewById(R.id.reg_a_year);
            showDataCitySpinner();
            showDataYearSpinner();
            userObj = new UserDB();
            reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");
            signUpBtnA.setOnClickListener(v -> signUpUserA());

        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void signUpUserA(){
        try{
            if(!wrongInput(name.getText().toString(),phone.getText().toString(),password.getText().toString(),
                    email.getText().toString(),city.getSelectedItem().toString(),year.getSelectedItem().toString())&&terms.isChecked()){
                if(mAuth!=null){
                    signUpBtnA.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT).show();
                                uid=mAuth.getCurrentUser().getUid();
                                updateToDB();
                                startActivity(new Intent(getActivity().getApplicationContext(), HomePageA.class));
                                signUpBtnA.setEnabled(true);
                                getActivity().finish();
                            }

                            )
                            .addOnFailureListener(e -> {
                                signUpBtnA.setEnabled(true);
                                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            });
                }
            }
            else{
                Toast.makeText(getContext(),"Please Fill All Fields",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

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

                final ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,yearArrayList);
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

                final ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,cityArrayList);
                city.setAdapter(arrayAdapterCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    public void updateToDB() {

        String nameStr = name.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();
        String passStr = password.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String cityStr = city.getSelectedItem().toString();
        String yearStr = year.getSelectedItem().toString();


        userObj.setName(nameStr);
        userObj.setPhone(phoneStr);
        userObj.setEmail(emailStr);
        userObj.setCity(cityStr);
        userObj.setBirthYear(yearStr);
        userObj.setUserType("3");
        userObj.setUserID(uid);


        String id = String.valueOf(userObj.getUserID());
        reff.child(id).setValue(userObj);



    }

//    // שם טלפון שנת לידה
//    private void userExist(String nameStr, String phoneStr, String yearStr) {
//        reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String dbName, dbPhone, dbYear;
//                for (DataSnapshot item : snapshot.getChildren()) {
//                    dbName = item.child("name").getValue(String.class);
//                    if(nameStr.equals(dbName)){
//                        dbPhone = item.child("phone").getValue(String.class);
//                        if(phoneStr.equals(dbPhone)){
//                            dbYear = item.child("birthYear").getValue(String.class);
//                            if(yearStr.equals(dbYear)) {
//                                Log.d("MSG","user exists");
//                            }
//                        }
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        objectRegFragment=inflater.inflate(R.layout.fragment_reg_a,container,false);

        initializeA();

        return objectRegFragment;
    }
    private boolean wrongInput(String nameStr, String phoneStr, String passStr, String emailStr, String cityStr, String yearStr){
        boolean flag = false;

        if(cityStr.equals("--בחר עיר--")){
            name.requestFocus();
            flag = true;
        }
        if(yearStr.equals("--בחר שנת לידה--")){
            name.setError("שדה חובה");
            name.requestFocus();
            flag = true;
        }
        if(nameStr.isEmpty()){
            name.setError("שדה חובה");
            name.requestFocus();
            flag = true;
        }
        if(phoneStr.isEmpty()){
            phone.setError("שדה חובה");
            phone.requestFocus();
            flag = true;
        }
        if(passStr.isEmpty()){
            password.setError("שדה חובה");
            password.requestFocus();
            flag = true;
        }

        if(passStr.length() < 6) {
            password.setError("סיסמא חייבת להכיל לפחות 6 תווים");
            password.requestFocus();
            flag = true;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("מייל לא תקין");
            email.requestFocus();
            flag = true;
        }

        return flag;

    }



}