package com.example.coronavolunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner city = (Spinner) findViewById(R.id.citySp);
         String cityQuery = "SELECT * FROM Cities" ;
       // fillSpinner(city, cityQuery);

        Spinner phoneStart = (Spinner) findViewById(R.id.phoneStart1);
        String phoneStartQuery = "SELECT * FROM Phone";
     ///   fillSpinner(phoneStart,phoneStartQuery);

        Spinner birthyear = (Spinner) findViewById(R.id.birthyear1);
        String birthYearQuery = "SELECT * FROM Year_Of_Birth";
     //   fillSpinner(birthyear,birthYearQuery);




        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
            public void insert (String input){

                try (Connection conn = DAL.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(input)) {
                    if (rs.next()) {
                        TextView name = (TextView) findViewById(R.id.nameInput1);
                        Spinner city = (Spinner) findViewById(R.id.citySp);
                        Switch car = (Switch) findViewById(R.id.car);
                        CheckBox lenguage = (CheckBox) findViewById(R.id.language);
                        CheckBox lenguage1 = (CheckBox) findViewById(R.id.language1);
                        CheckBox lenguage2 = (CheckBox) findViewById(R.id.language2);
                        Spinner phoneStart = (Spinner) findViewById(R.id.phoneStart1);
                        EditText phoneEnd = (EditText) findViewById(R.id.phoneEnd1);

                        Switch volunteer = (Switch) findViewById(R.id.volunteer);
                        EditText password = (EditText) findViewById(R.id.password1);
                        Spinner birthyear = (Spinner) findViewById(R.id.birthyear1);
                        EditText about = (EditText) findViewById(R.id.about);
                        CheckBox terms = (CheckBox) findViewById(R.id.terms1);

                        input=" INSERT INTO Users(Full_Name,Password,PhoneStart,PhoneEnd,City,Languages,Year_Of_Birth,Own_A_Car,Volunteer) VALUES("+name+","+password+","+phoneStart+","+phoneEnd+","+city+","+lenguage+","+birthyear+","+car+")";
                        stmt.execute(input);

                    }
                } catch (SQLException throwables) {
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
        spin.setAdapter(aa);
    }

    private ArrayList<String> SpinnerList(String query){
        ArrayList<String> spinnerList = new ArrayList<String>();
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