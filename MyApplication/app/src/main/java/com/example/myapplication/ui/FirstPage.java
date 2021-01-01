package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;



public class FirstPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_first_page,container,false);
        Button btnFragment=(Button)view.findViewById(R.id.pick_login);
        Button reg=(Button)view.findViewById(R.id.pick_signup);

        btnFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new LoginV2());
                fr.commit();
            }
        });
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                assert getFragmentManager() != null;
                FragmentTransaction fr1=getFragmentManager().beginTransaction();
                fr1.replace(R.id.fragment_container,new PickReg());
                fr1.commit();
            }
        });
        return view;
    }
}