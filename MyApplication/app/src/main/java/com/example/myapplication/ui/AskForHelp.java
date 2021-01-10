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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;


public class AskForHelp extends Fragment {
    private EditText enterPhone;
    private RequestsDB reqObj;
    private DatabaseReference reffRequest;
    public AskForHelp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_ask_for_help,container,false);
        reqObj=new RequestsDB();
        enterPhone=view.findViewById(R.id.editTextPhone);
        Button btnFragment=(Button)view.findViewById(R.id.ask_for_reg_help);
        reffRequest = FirebaseDatabase.getInstance().getReference().child("Requests");
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRequest();
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new FirstPage()).addToBackStack("ask-for-reg-help-confirm");
                fr.commit();
            }
        });
        return view ;
    }
    private void addRequest(){
        try{
            if(!enterPhone.getText().toString().isEmpty()){
                updateDB();

            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void updateDB(){
        String openDateTime = Calendar.getInstance().getTime().toString();
        String phone=enterPhone.getText().toString().trim();


        reqObj.setType("עזרה בהרשמה");
        reqObj.setPleggerUid(phone);


        reffRequest.child(openDateTime).setValue(reqObj);

    }
}