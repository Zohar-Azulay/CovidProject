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


public class new_pledge extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_pledge_details, container, false);
        Button confirm_pledge = view.findViewById(R.id.btn_new_req_confirm);
        confirm_pledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr_newp = getFragmentManager().beginTransaction();
                fr_newp.replace(R.id.fragment_pledger_container, new main_p());
                fr_newp.commit();
            }
        });
        return view;
    }
}

