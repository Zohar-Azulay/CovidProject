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

public class reg_v extends Fragment {
    private EditText name, email, phone ,password;
    private Button signUpBtn;
    private CheckBox terms;
    private View objectRegFragment;
    private FirebaseAuth mAuth;
    private DatabaseReference reff;
    private UserDB userObj;
    private String uid;
    private final String TAG = "reg_v";


    //SPINNERS
    private Spinner year, city;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffSpinnerYear = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> yearArrayList = new ArrayList<>();
    private ArrayList<String> cityArrayList = new ArrayList<>();

    public reg_v(){
        //empty
    }

    private  void initialize(){
        try{
            mAuth= FirebaseAuth.getInstance();
            name=objectRegFragment.findViewById(R.id.reg_v_name);
            email=objectRegFragment.findViewById(R.id.reg_v_email);
            phone=objectRegFragment.findViewById(R.id.reg_v_phone);
            password=objectRegFragment.findViewById(R.id.reg_v_password);
            signUpBtn=objectRegFragment.findViewById(R.id.reg_v_finish);
            terms=objectRegFragment.findViewById(R.id.reg_v_takanon);
            city =objectRegFragment.findViewById(R.id.reg_v_city);
            year = objectRegFragment.findViewById(R.id.reg_v_year);
            showDataCitySpinner();
            showDataYearSpinner();
            userObj = new UserDB();
            reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");
            signUpBtn.setOnClickListener(v -> signUpUser());

        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void signUpUser(){
        try{
            if(!wrongInput(name.getText().toString(),phone.getText().toString(),password.getText().toString(),
                    email.getText().toString(),city.getSelectedItem().toString(),year.getSelectedItem().toString())&&terms.isChecked()){
                if(mAuth!=null){
                    signUpBtn.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                      @Override
                                                      public void onSuccess(AuthResult authResult) {
                                                          Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT).show();
                                                          uid=mAuth.getCurrentUser().getUid();
                                                          updateToDB();
                                                          startActivity(new Intent(getActivity().getApplicationContext(), HomePageV.class));
                                                          signUpBtn.setEnabled(true);
                                                          getActivity().finish();
                                                      }
                                                  }

                            )
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    signUpBtn.setEnabled(true);
                                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
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
        userObj.setUserType("1");
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
        objectRegFragment=inflater.inflate(R.layout.fragment_reg_v,container,false);

        initialize();

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