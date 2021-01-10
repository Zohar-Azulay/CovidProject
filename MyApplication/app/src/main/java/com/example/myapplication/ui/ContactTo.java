package com.example.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

//add this
public class ContactTo extends Fragment {
    private Button dial;
    private TextView phone;

    public ContactTo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact_to, container, false);
        dial=view.findViewById(R.id.phoneToDial);
        phone=view.findViewById(R.id.phoneNumberToDial);
        String dialPhone=phone.getText().toString();// need to change to the phone number from the database
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+dialPhone));
                startActivity(intent);
            }
        });


        return view;
    }
}
//      Intent intent = new Intent(Intent.ACTION_DIAL);
//      intent.setData(Uri.parse("tel:0123456789"));
//      startActivity(intent);