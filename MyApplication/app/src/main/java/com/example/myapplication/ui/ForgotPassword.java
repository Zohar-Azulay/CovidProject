package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;
import android.widget.Button;
import com.example.myapplication.ui.ui.login.LoginFragment;

public class ForgotPassword extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_forgot_password,container,false);
        Button btnFragment=(Button)view.findViewById(R.id.button);

        btnFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                assert getFragmentManager() != null;
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new PasswordReset());
                fr.commit();
            }
        });
        return view;

    }
}