package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;


public class AdminPreReg extends Fragment {

    public AdminPreReg() {
        // Required empty public constructor
    }
    private Button confirmAdmin;
    private EditText enterKey;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_pre_reg, container, false);
        confirmAdmin=view.findViewById(R.id.confirm_admin);
        enterKey=view.findViewById(R.id.textKey);
        //String key=enterKey.getText().toString();

        confirmAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKey();
            }
        });


        return view;
    }
    private void checkKey(){
        if(!enterKey.getText().toString().isEmpty()){
            if(enterKey.getText().toString().equals("QaWsEd123")){
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new reg_a()).addToBackStack("reg-admin-confirm");
                fr.commit();
            }
            else{
                Toast.makeText(getContext(), "wrong key returning home", Toast.LENGTH_SHORT).show();
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new FirstPage()).addToBackStack("back-to-homepage");
                fr.commit();
            }
        }
        else{
            Toast.makeText(getContext(), "Please enter the key first", Toast.LENGTH_SHORT).show();
        }
    }
}