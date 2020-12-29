package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.ui.login.LoginFragment;

public class PasswordReset extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_password_reset,container,false);
        Button btnFragment=(Button)view.findViewById(R.id.button4);

        btnFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new LoginV2());
                fr.commit();
            }
        });
        return view;
    }
}