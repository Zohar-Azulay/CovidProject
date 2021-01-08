package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends Fragment {

    private View objectForgotFragment;
    private Button resetPassword;
    private EditText resetEMailInput;
    private FirebaseAuth mAuth;

    public ForgotPassword(){
        //empty constructor
    }

    private void initializeVariables(){
        try{
            mAuth=FirebaseAuth.getInstance();
            resetEMailInput=objectForgotFragment.findViewById(R.id.resetEmail);
            resetPassword=objectForgotFragment.findViewById(R.id.resetPassBtn);

            resetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetPassword.setClickable(false);
                    resPass();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void resPass()
    {
        try {

            if (!resetEMailInput.getText().toString().isEmpty()) {
                if (mAuth != null) {
                    mAuth.sendPasswordResetEmail(resetEMailInput.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //flag=true;
                                        resetPassword.setEnabled(false);
                                        Toast.makeText(getContext(), "Please check your email for reset", Toast.LENGTH_LONG).show();
                                        assert getFragmentManager() != null;

                                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                                        fr.replace(R.id.fragment_container, new LoginV2()).addToBackStack("password-reset");
                                        fr.commit();
                                    }
                                    else{
                                        resetPassword.setEnabled(true);
                                        Toast.makeText(getContext(), "Entered email does not exist", Toast.LENGTH_LONG).show();
                                        resPass();
                                    }
                                }
                    });
                }

            }
            else {
                Toast.makeText(getContext(), "Please enter your email first", Toast.LENGTH_SHORT).show();

            }


        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectForgotFragment=inflater.inflate(R.layout.fragment_forgot_password,container,false);
        initializeVariables();

        return objectForgotFragment;
    }
}