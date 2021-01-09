package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;


public class homePageV extends Fragment {
    private FirebaseAuth mAuth;
    private Button openReq;
    private String id = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_home_page_v, container, false);
        mAuth=FirebaseAuth.getInstance();
//        Button btnopenReq = (Button) view.findViewById(R.id.button30);
        Button btnedit = (Button) view.findViewById(R.id.button31);
        Button btnvacation = (Button) view.findViewById(R.id.button36);
        Button btnsingout = (Button) view.findViewById(R.id.button35);      //-----------change the singout----------
        Button back2 = (Button) view.findViewById(R.id.button30);
        Button btnFeedB = (Button) view.findViewById(R.id.button2);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),openReq.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0,0);

            }
        });

//        btnFeedB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),FeedBackV.class);
//                startActivity(intent);
//                ((Activity) getActivity()).overridePendingTransition(0,0);
//
//            }
//        });

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