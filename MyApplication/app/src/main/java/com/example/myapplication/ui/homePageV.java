package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;


public class homePageV extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_home_page_v, container, false);
        Button btnopenReq = (Button) view.findViewById(R.id.button30);
        Button btnedit = (Button) view.findViewById(R.id.button31);
        Button btnvacation = (Button) view.findViewById(R.id.button36);

        btnopenReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new openRegV());
                fr.commit();
            }

        });
        btnvacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new VacationV());
                fr.commit();
            }

        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new editDetV());
                fr.commit();
            }

        });

        return view;
    }
}