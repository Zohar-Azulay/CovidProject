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

        vl.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr1=getFragmentManager().beginTransaction();
            fr1.replace(R.id.fragment_container,new reg_v());
            fr1.commit();
        });
        hp.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr2=getFragmentManager().beginTransaction();
            fr2.replace(R.id.fragment_container,new reg_p());
            fr2.commit();
        });
        ad.setOnClickListener(V -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr3=getFragmentManager().beginTransaction();
            fr3.replace(R.id.fragment_container,new reg_a());
            fr3.commit();
        });
        return view;
    }
}
