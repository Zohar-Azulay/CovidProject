package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;


public class HomeFragmentV extends Fragment {



    public HomeFragmentV() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_home_v,container,false);
        Button btnFragment=(Button)view.findViewById(R.id.pick_navigation);
        btnFragment.setOnClickListener(v -> {
            assert getFragmentManager() != null;
            FragmentTransaction fr=getFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container_v,new ContactPledger()).addToBackStack("pick-navigation");
            fr.commit();
        });
        return view;
    }
}