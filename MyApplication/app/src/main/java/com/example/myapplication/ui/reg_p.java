package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reg_p extends Fragment {

    private EditText name, password, email, phone;
    private Button signUpBtn,askForHelp;
    private CheckBox terms;
    private View ObjectRegPFragment;
    private FirebaseAuth mAuth;
    private DatabaseReference reff;
    private UserDB userObj;
    private ProgressBar progressBar;
    private String uid;
    private final String TAG = "reg_p";

    //SPINNERS
    private Spinner year, city;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffSpinnerYear = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> yearArrayList = new ArrayList<>();
    private ArrayList<String> cityArrayList = new ArrayList<>();

    public reg_p() {
        // Required empty public constructor
    }

    private void initializeUser(){
        try{
            mAuth = FirebaseAuth.getInstance();
            askForHelp=ObjectRegPFragment.findViewById(R.id.pick_reg_help);
            name = ObjectRegPFragment.findViewById(R.id.reg_p_name);
            email = ObjectRegPFragment.findViewById(R.id.reg_p_email);
            password = ObjectRegPFragment.findViewById(R.id.reg_p_password);
            phone = ObjectRegPFragment.findViewById(R.id.reg_p_cell_number);
            signUpBtn = ObjectRegPFragment.findViewById(R.id.btn_reg_confirm);
            progressBar = ObjectRegPFragment.findViewById(R.id.progress_bar_p);
            terms = ObjectRegPFragment.findViewById(R.id.taknon_check_p);
            city = ObjectRegPFragment.findViewById(R.id.reg_p_city);
            year = ObjectRegPFragment.findViewById(R.id.reg_p_year);

            showDataCitySpinner();
            showDataYearSpinner();

            userObj = new UserDB();
            reff = FirebaseDatabase.getInstance().getReference().child("משתמשים");

            signUpBtn.setOnClickListener(v -> signUpUser());
            askForHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert getFragmentManager() != null;
                    FragmentTransaction fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new AskForHelp()).addToBackStack("pick-ask-for-help");
                    fr.commit();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void signUpUser() {
        try{
            if(!wrongInput(name.getText().toString(),phone.getText().toString(),
                    password.getText().toString(), email.getText().toString(),
                    city.getSelectedItem().toString(),year.getSelectedItem().toString())
                    &&terms.isChecked()){
                if(mAuth!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    signUpBtn.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(getContext(), "נרשמת בהצלחה", Toast.LENGTH_SHORT).show();
                                uid = mAuth.getCurrentUser().getUid();
                                progressBar.setVisibility(View.INVISIBLE);
                                updateDB();
                                startActivity(new Intent(getActivity().getApplicationContext(), HomePageP.class));
                                signUpBtn.setEnabled(true);
                                getActivity().finish();
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpBtn.setEnabled(true);
                                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
            else{
                Toast.makeText(getContext(),"יש למלא את כל הפרטים",Toast.LENGTH_SHORT).show();
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
                // TODO -- Dates validation
                // אם קורס - לוודא שיש גרשיים בתאריכים


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

    public void updateDB() {

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
        userObj.setUserType("2");
        userObj.setUserID(uid);

        String id = String.valueOf(userObj.getUserID());
        reff.child(id).setValue(userObj);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ObjectRegPFragment=inflater.inflate(R.layout.fragment_reg_p,container,false);

        initializeUser();

        return ObjectRegPFragment;
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

        if(passStr.length() < 8) {
            password.setError("סיסמא חייבת להכיל לפחות 8 תווים");
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



/*
    @Override
    public void onClick(View v) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        if(register()) {
                            Intent intent = new Intent(this,EditPersonalDetails.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(Throwable::printStackTrace);
    }
    private boolean register(){
        String nameStr = name.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();
        String passStr = password.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
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
                                        Toast.makeText(RegisterUser.this,"Failed to register! Try again!11",Toast.LENGTH_LONG).show();
                                }
                            });
                        }else
                            Toast.makeText(RegisterUser.this,"Failed to register! Try again!22",Toast.LENGTH_LONG).show();
                    }
                });
        return false;
    }
 */
}




//package com.example.myapplication.ui;
//
//import android.nfc.Tag;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.myapplication.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.type.TimeOfDayOrBuilder;
//
//public class reg_p extends Fragment {
//
//    View obj_reg_p_fragment;
//    private EditText fill_name, fill_cellphone, fill_email, fill_password;
//    private Button confirm_reg;
//    private String TAG = "newUser";
//    private CheckBox terms;
//    private ProgressBar progressBar;
//
//    private DatabaseReference reff;
//    private UserDB userObj;
//
//    private FirebaseAuth obj_FB_Auth;







//    @Override
//    public void onComplete(@NonNull Task<AuthResult> task) {
//        if (task.isSuccessful()) {
//            Log.d(TAG, "createUserWithEmail: success");
//            FirebaseUser newUser = obj_FB_Auth.getCurrentUser();
//            updateUI(newUser);
//        } else {
//            Log.w(TAG, "createUserWithEmail: failure", task.getException());
//            Toast.makeText(EmailPasswordActivity.this, "Authntication failed.", Toast.LENGTH_SHORT).show();
//            updateUI(null);
//        }
//    }
//
//    public void createUser(View objView)
//    {
//        try
//        {
//            if(!fill_email.getText().toString().isEmpty()&&
//                    !fill_password.getText().toString().isEmpty()&&
//                    !fill_name.getText().toString().isEmpty() &&
//                    !fill_cellphone.getText().toString().isEmpty() &&
//                    !confirm_reg.getText().toString().isEmpty()) {
////                obj_FB_Auth = FirebaseAuth.getInstance();
//                obj_FB_Auth.createUserWithEmailAndPassword(fill_email, fill_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>())
//            }
//            else
//            {
//                Toast.makeText(getContext(), "נא למלא את כל השדות", Toast.LENGTH_LONG).show();
//            }
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }


//    private void attachToXML()
//    {
//        try
//        {
//            fill_name = obj_reg_p_fragment.findViewById(R.id.fullname_fill_h);
//            fill_cellphone = obj_reg_p_fragment.findViewById(R.id.cellphone_reg_h);
//            fill_email = obj_reg_p_fragment.findViewById(R.id.email_reg_h);
//            fill_password = obj_reg_p_fragment.findViewById(R.id.password_reg_h);
//            confirm_reg = obj_reg_p_fragment.findViewById(R.id.btn_reg_confirm);
//            progressBar = obj_reg_p_fragment.findViewById(R.id.progress_bar_p);
//
//            obj_FB_Auth = FirebaseAuth.getInstance();
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public reg_p() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        obj_reg_p_fragment = inflater.inflate((R.layout.fragment_reg_p, container, false);
//        attachToXML();
//
//        return inflater.inflate((R.layout.fragment_reg_p, container, false);
//    }
//
