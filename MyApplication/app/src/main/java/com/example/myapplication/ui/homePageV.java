package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;


public class homePageV extends Fragment {
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_home_page_v, container, false);
        mAuth=FirebaseAuth.getInstance();
        Button btnopenReq = (Button) view.findViewById(R.id.button30);
        Button btnedit = (Button) view.findViewById(R.id.button31);
        Button btnvacation = (Button) view.findViewById(R.id.button36);
        Button btnsingout = (Button) view.findViewById(R.id.button35);      //-----------change the singout----------

        btnopenReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new openRegV()).addToBackStack("open_req");
                fr.commit();
            }

        });
        btnvacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new VacationV()).addToBackStack("open_vacation");
                fr.commit();
            }

        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                assert getFragmentManager() != null;
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new editDetV()).addToBackStack("to edit");
                fr.commit();
            }

        });
        btnsingout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                if (mAuth!=null){
                    mAuth.signOut();
                    assert getFragmentManager() != null;
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new LoginV2()).addToBackStack("singout");
                    fr.commit();
                }
            }

        });

        return view;
    }
}