package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;


public class edit_details extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_details, container, false);
        Button btnEditD = view.findViewById(R.id.btn_save_changes);
        btnEditD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr_edit = getFragmentManager().beginTransaction();
                fr_edit.replace(R.id.fragment_pledger_container, new main_p());
                fr_edit.commit();
            }
        });
        return view;
    }
}
