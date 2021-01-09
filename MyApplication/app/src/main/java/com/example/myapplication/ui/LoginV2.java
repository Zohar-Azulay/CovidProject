package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginV2 extends Fragment {
    private View objectSignInFragment;
    private FirebaseAuth objectFirebaseAuth;
    private DatabaseReference ref;
    private EditText userEmailET,userPasswordET;
    private Button signInBtn,forgotBtn;
    private ProgressBar objectProgressBar;
    private String uid;
    public LoginV2(){
        //empty
    }
    private  void initializeVariables(){
        try{
            objectFirebaseAuth=FirebaseAuth.getInstance();

            userEmailET=objectSignInFragment.findViewById(R.id.email);
            userPasswordET=objectSignInFragment.findViewById(R.id.password);
            signInBtn=objectSignInFragment.findViewById(R.id.login_v2);
            forgotBtn = objectSignInFragment.findViewById(R.id.pick_forgot_password);
            objectProgressBar=objectSignInFragment.findViewById(R.id.loading);

            signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInUser();
                }
            });
            forgotBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert getFragmentManager() != null;
                    FragmentTransaction fr=getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container,new ForgotPassword()).addToBackStack("forgot-password");
                    fr.commit();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void signInUser()
    {
        try{
            if(!userEmailET.getText().toString().isEmpty() && !userPasswordET.getText().toString().isEmpty()){
                if(objectFirebaseAuth!=null){
                    objectProgressBar.setVisibility(View.VISIBLE);
                    signInBtn.setEnabled(false);

                    objectFirebaseAuth.signInWithEmailAndPassword(userEmailET.getText().toString(),
                            userPasswordET.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    uid=objectFirebaseAuth.getCurrentUser().getUid();
                                    ref= FirebaseDatabase.getInstance().getReference().child("משתמשים").child(uid);
                                    ref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String user_Type=snapshot.child("userType").getValue().toString();
                                            switch (user_Type) {
                                                case "1":
                                                    startActivity(new Intent(getActivity().getApplicationContext(), HomePageV.class));
                                                    objectProgressBar.setVisibility(View.INVISIBLE);

                                                    signInBtn.setEnabled(true);
                                                    getActivity().finish();
                                                    break;
                                                case "2":
                                                    startActivity(new Intent(getActivity().getApplicationContext(), HomePageP.class));
                                                    objectProgressBar.setVisibility(View.INVISIBLE);

                                                    signInBtn.setEnabled(true);
                                                    getActivity().finish();
                                                    break;
                                                case "3":
                                                    startActivity(new Intent(getActivity().getApplicationContext(), HomePageA.class));
                                                    objectProgressBar.setVisibility(View.INVISIBLE);

                                                    signInBtn.setEnabled(true);
                                                    getActivity().finish();
                                                    break;
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }

                )
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            objectProgressBar.setVisibility(View.INVISIBLE);
                            
                            signInBtn.setEnabled(true);
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            else{
                Toast.makeText(getContext(),"Please Fill Both Fields",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        objectSignInFragment=inflater.inflate(R.layout.fragment_login_v2,container,false);
        initializeVariables();

        return objectSignInFragment;
    }
}
