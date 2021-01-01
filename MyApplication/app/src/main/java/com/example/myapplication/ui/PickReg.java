package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;



public class PickReg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_pick_reg,container,false);

        Button vl=view.findViewById(R.id.chooseVal);

        Button hp=view.findViewById(R.id.chooseHelp);

        Button ad= view.findViewById(R.id.chooseAdmin);
//temp
        vl.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr1=getFragmentManager().beginTransaction();
            fr1.replace(R.id.fragment_container,new LoginV2());//update after u get reg page of vl
            fr1.commit();
        });
        hp.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr2=getFragmentManager().beginTransaction();
            fr2.replace(R.id.fragment_container,new FirstPage());//update after u get reg page of hp
            fr2.commit();
        });
        ad.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr3=getFragmentManager().beginTransaction();
            fr3.replace(R.id.fragment_container,new ForgotPassword());//update after u get reg page of ad
            fr3.commit();
        });
        return view;
    }
}
