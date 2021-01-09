package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class editDetV extends Fragment {


    private EditText change_phone;
    private Spinner city_spin;
    private DatabaseReference reffSpinnerCity = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffPhone = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> cityArrayList = new ArrayList<>();
    private String uid;

    private FirebaseDatabase reff;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_det_v, container, false);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null)
            uid = mAuth.getCurrentUser().getUid();

        Button btnEditD = view.findViewById(R.id.btn_save_changesV);
        city_spin = view.findViewById(R.id.edit_details_cityV);
        change_phone = view.findViewById(R.id.edit_details_cellnumberV);
        showDataCitySpinner();

        btnEditD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;

                String cityStr = city_spin.getSelectedItem().toString();
                String phoneStr = change_phone.getText().toString().trim();
                editData(cityStr, phoneStr);
                FragmentTransaction fr_edit = getFragmentManager().beginTransaction();
                fr_edit.replace(R.id.fragment_container, new homePageV()).addToBackStack("Save changes");
                fr_edit.commit();
            }
        });

        TextView password_reset = view.findViewById(R.id.password_resetV);
        password_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr_pwReset = getFragmentManager().beginTransaction();
                fr_pwReset.replace(R.id.fragment_container, new ForgotPassword()).addToBackStack("Password reset");
                fr_pwReset.commit();
            }
        });

        return view;
    }

    private void showDataCitySpinner() {
        reffSpinnerCity.child("ערים").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //city
                cityArrayList.clear();
                for (DataSnapshot item : snapshot.getChildren())
                    cityArrayList.add(item.child("עיר").getValue(String.class));

                ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(getActivity(),
                       android.R.layout.simple_spinner_dropdown_item, cityArrayList);
                city_spin.setAdapter(arrayAdapterCity);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }
    private void editData(String phoneStr, String cityStr){
        if (!phoneStr.isEmpty())
            reffPhone.child("משתמשים").child(String.valueOf(uid)).child("phone").setValue(phoneStr);
        if (!cityStr.equals("--בחר עיר--"))
            reffSpinnerCity.child("משתמשים").child(String.valueOf(uid)).child("city").setValue(cityStr);
    }
}