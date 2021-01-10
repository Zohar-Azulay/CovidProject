package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class main_p extends Fragment {


    private String id ="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_p, container, false);

        Button btnNewP = view.findViewById(R.id.btn_ask);
        Button btnYourP = view.findViewById(R.id.btn_your_pledges);
        ImageButton btnSuppot = view.findViewById(R.id.btn_support);
        Button btnEditD = view.findViewById(R.id.btn_edit_details);

        btnSuppot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr_support = getFragmentManager().beginTransaction();
                fr_support.replace(R.id.fragment_pledger_container, new fragment_support()).addToBackStack("switch to support fragment");
                fr_support.commit();
            }
        });

        btnNewP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr_newP = getFragmentManager().beginTransaction();
                fr_newP.replace(R.id.fragment_pledger_container, new new_pledge()).addToBackStack("switch to new pledge fragment");
                fr_newP.commit();
            }
        });

        btnYourP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                assert getFragmentManager() != null;
                Intent intent = new Intent(getActivity().getApplication(), pledges_list.class);
                startActivity(intent);
//                ((Activity) getActivity()).overridePendingTransition(0,0);
//                FragmentTransaction fr_newP = getFragmentManager().beginTransaction();
//                fr_newP.replace(R.id.fragment_pledger_container, new pledges_list()).addToBackStack("switch to new pledge fragment");
//                fr_newP.commit();
            }
        });

        btnEditD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                FragmentTransaction fr_edit = getFragmentManager().beginTransaction();
                fr_edit.replace(R.id.fragment_pledger_container, new edit_details()).addToBackStack("switch to edit details fragment");
                fr_edit.commit();
            }
        });

        return view;
    }
}
