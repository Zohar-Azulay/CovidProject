package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class WelfareFactors extends Activity {

        private String id = "";
        ListView simpleList;
        ArrayList<String> welfareList=new ArrayList<>();
        ArrayList<String> phoneList=new ArrayList<>();

        Button btn;
        @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_welfare_factors);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);

        simpleList = (ListView)findViewById(R.id.welfareList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, welfareList);
        simpleList.setAdapter(arrayAdapter);

        simpleList = (ListView)findViewById(R.id.phoneList);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, phoneList);
        simpleList.setAdapter(arrayAdapter1);


            welfareList.add(" עיריית באר-שבע");
            welfareList.add("עיריית אופקים");
            welfareList.add("עיריית נתיבות");
            welfareList.add("עיריית אשקלון");
            welfareList.add("עיריית אשדוד");
            welfareList.add("עיריית ירושלים");
            welfareList.add("עיריית אילת");
            welfareList.add("עיריית תל-אביב");
            welfareList.add("");

            phoneList.add("08-646-3666");
            phoneList.add("08-992-8555");
            phoneList.add("08-993-8711");
            phoneList.add("08-679-2306");
            phoneList.add("08-854-5454");
            phoneList.add("02-629-6666");
            phoneList.add("08-636-7106");
            phoneList.add("03-521-8666");

            btn = (Button) findViewById(R.id.welfareBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WelfareFactors.this, HomeAdmin.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

}
