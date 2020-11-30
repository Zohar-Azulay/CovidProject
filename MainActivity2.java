package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAL.DAL;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongViewCast")
            @Override
            public void onClick(View v) {
            }

            public void insert(String input) {


                try (Connection conn = DAL.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(input)) {
                    if (rs.next()) {

                        TextView name = (TextView) findViewById(R.id.inpname);
                        TextView year = (TextView) findViewById(R.id.inputyear);
                        Spinner city = (Spinner) findViewById(R.id.spinnersity);
                        Spinner phoneStart = (Spinner) findViewById(R.id.spinnerphone);
                        TextView street = (TextView) findViewById(R.id.inputstreet);
                        TextView home = (TextView) findViewById(R.id.Nhome);
                        TextView phoneEnd = (TextView) findViewById(R.id.inputphone);
                        Button finish_reg = (Button) findViewById(R.id.finishReg);
                        CheckBox terms = (CheckBox) findViewById(R.id.termsid);
                        Spinner language = (Spinner) findViewById(R.id.spinnerlan);
                        TextView password = (TextView)findViewById((R.id.inputpass));
                        input = " INSERT INTO Users(Full_Name,Password,PhoneStart,PhoneEnd,City,Languages,Year_Of_Birth,Street,Home,) VALUES(" + name + "," + password + "," + phoneStart + "," + phoneEnd + "," + city + "," + language + "," + year + ","+street+","+home+")";
                        stmt.execute(input);

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            public void Spinner(String Cities){
                try (Connection conn = DAL.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(Cities)) {
                    //  Cities="SELECT * FROM Cities" ;
                    while(rs.next()){

                        //Spinner city = (Spinner) findViewById(R.id.citySp1);
                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }


    private void fillSpinner(Spinner spin, String query){
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayList<String> spinner = SpinnerList(query);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private ArrayList<String> SpinnerList(String query){
        ArrayList<String> spinnerList = new ArrayList<String>();
        //String query="SELECT * FROM Cities" ;
        try (Connection conn = DAL.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while(rs.next())
                spinnerList.add(rs.getString(1));
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return spinnerList;
    }
}
