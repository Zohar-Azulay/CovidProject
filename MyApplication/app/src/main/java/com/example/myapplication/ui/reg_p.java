package com.example.myapplication.ui;

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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reg_p extends Fragment {

    private EditText name, password, email, phone;
    private Button signUpBtn;
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

    private void initializeUser() {
        try {
            mAuth = FirebaseAuth.getInstance();
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

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void signUpUser() {
        try {
            if (!wrongInput(name.getText().toString(), phone.getText().toString(),
                    password.getText().toString(), email.getText().toString(),
                    city.getSelectedItem().toString(), year.getSelectedItem().toString())
                    && terms.isChecked()) {
                if (mAuth != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    signUpBtn.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(getContext(), "נרשמת בהצלחה", Toast.LENGTH_SHORT).show();
                                uid = mAuth.getCurrentUser().getUid();
                                progressBar.setVisibility(View.INVISIBLE);
                                updateDB();
                                startActivity(new Intent(getActivity().getApplicationContext(), PledgerActivity.class));
                                signUpBtn.setEnabled(true);
                                getActivity().finish();
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpBtn.setEnabled(true);
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                Toast.makeText(getContext(), "יש למלא את כל הפרטים", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showDataYearSpinner() {
        reffSpinnerYear.child("שנת לידה").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //year
                yearArrayList.clear();
                for (DataSnapshot item : snapshot.getChildren())
                    yearArrayList.add(item.child("שנה").getValue(String.class));
                // TODO -- Dates validation
                // אם קורס - לוודא שיש גרשיים בתאריכים


                final ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, yearArrayList);
                year.setAdapter(arrayAdapterYear);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

                final ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, cityArrayList);
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
        ObjectRegPFragment = inflater.inflate(R.layout.fragment_reg_p, container, false);

        initializeUser();

        return ObjectRegPFragment;
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

        if (passStr.length() < 8) {
            password.setError("סיסמא חייבת להכיל לפחות 8 תווים");
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