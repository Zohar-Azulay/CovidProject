package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAL.DAL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
                public void insert(String input) {


                    try (Connection conn = DAL.connect();
                         Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery(input)) {
                        if (rs.next()) {
                EditText phoneNum=(EditText) findViewById(R.id.EndPhone2);
                EditText passwordNum=(EditText)findViewById(R.id.editTextTextPassword2);
               // EditText Endphone=(EditText)findViewById(R.id.ephone);
                CheckBox checkBox=(CheckBox)findViewById((R.id.rememberid));
                Spinner startphone =(Spinner)findViewById((R.id.spinner)) ;

                stmt.execute(input);

                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            public void Spinner(String PhoneStart) {
                try (Connection conn = DAL.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(PhoneStart)) {
                     PhoneStart="SELECT * FROM Phone" ;
                    while (rs.next()) {

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();


                }
            }


        });
            Button reg= (Button)findViewById(R.id.button2);
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent opensec =new Intent(this,MainActivity2.class);
                    startActivity(opensec);
                }
            });
    }
}