package com.example.myapplication.ui;

<<<<<<< HEAD
import android.os.Bundle;

import androidx.fragment.app.Fragment;
=======
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
>>>>>>> Zohar

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstPage newInstance(String param1, String param2) {
        FirstPage fragment = new FirstPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
=======
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.ui.login.LoginFragment;


public class FirstPage extends Fragment {
>>>>>>> Zohar

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_page, container, false);
    }
=======
        View view;
        view = inflater.inflate(R.layout.fragment_first_page,container,false);
        Button btnFragment=(Button)view.findViewById(R.id.button3);

        btnFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new LoginFragment());
                fr.commit();
            }
        });
        return view;

    }


>>>>>>> Zohar
}