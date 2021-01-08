package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class new_pledge extends Fragment {


    private View viewObj;

    private EditText day, month, year, hour, minute;
    private Spinner type;
    private Button confirm_pledge;
    private DatabaseReference reffSpinnerType = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference reffRequest;
    private ArrayList<String> typeArrayList = new ArrayList<>();
    private String uid;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    private Requests reqObj;

    public new_pledge(){
        // Empty constructor;
    }

    private void initialize(){
        try {
            hour = viewObj.findViewById(R.id.new_pledge_hour);

            confirm_pledge = viewObj.findViewById(R.id.btn_new_req_confirm);
            day = viewObj.findViewById(R.id.new_pledge_day);
            month = viewObj.findViewById(R.id.new_pledge_month);
            year = viewObj.findViewById(R.id.new_pledge_year);
            reqObj = new Requests();
            minute = viewObj.findViewById(R.id.new_pledge_minute);
            type = viewObj.findViewById(R.id.new_pledge_type_spinner);
            showDataTypeSpinner();
            //reff = FirebaseDatabase.getInstance().getReference().child("Req_types");
            reffRequest = FirebaseDatabase.getInstance().getReference().child("Requests");

            confirm_pledge.setOnClickListener(v -> createNewPledge());

        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void createNewPledge(){
        try{
            if (!day.getText().toString().isEmpty()&& !month.getText().toString().isEmpty() &&
                   !year.getText().toString().isEmpty() &&
                    !hour.getText().toString().isEmpty() &&
                    !minute.getText().toString().isEmpty() &&
                    !type.getSelectedItem().toString().equals("")) {
                if (mAuth != null) {
                    //confirm_pledge.setEnabled(false);

                    uid = mAuth.getCurrentUser().getUid();

                    updateDB();
                    confirm_pledge.setEnabled(true);
                    assert getFragmentManager() != null;
                    FragmentTransaction fr_newp = getFragmentManager().beginTransaction();
                    fr_newp.replace(R.id.fragment_container_p, new HomeFragmentP());
                    fr_newp.commit();

                }
                else{
                    Toast.makeText(getContext(), "user shit", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getContext(), "יש להשלים את כל הפרטים", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showDataTypeSpinner() {
        reffSpinnerType.child("Req_types").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //type
                typeArrayList.clear();
                for (DataSnapshot item : snapshot.getChildren())
                    typeArrayList.add(item.child("type").getValue(String.class));

                final ArrayAdapter<String> arrayAdapterType= new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item, typeArrayList);
                type.setAdapter(arrayAdapterType);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){
            }
        });
    }

    public void updateDB(){

        String dayStr = day.getText().toString().trim();
       // Toast.makeText(getContext(), dayStr, Toast.LENGTH_SHORT).show();
        String monthStr = month.getText().toString().trim();
        //Toast.makeText(getContext(), monthStr, Toast.LENGTH_SHORT).show();
        String yearStr = year.getText().toString().trim();
       // Toast.makeText(getContext(), yearStr, Toast.LENGTH_SHORT).show();
        String hourStr = hour.getText().toString().trim();
       // Toast.makeText(getContext(), hourStr, Toast.LENGTH_SHORT).show();
        String minuteStr = minute.getText().toString().trim();
       // Toast.makeText(getContext(), minuteStr, Toast.LENGTH_SHORT).show();
        String typeStr = type.getSelectedItem().toString();
       // Toast.makeText(getContext(), typeStr, Toast.LENGTH_SHORT).show();
        String dateStr = dayStr + '/' + monthStr + '/' + yearStr;
        //Toast.makeText(getContext(), dateStr, Toast.LENGTH_SHORT).show();
        String timeStr = hourStr + ':' + minuteStr;
        //Toast.makeText(getContext(), timeStr, Toast.LENGTH_SHORT).show();
        reqObj.setPledger_uid(uid);
       // Toast.makeText(getContext(), uid, Toast.LENGTH_SHORT).show();
        reqObj.setHelper_uid("null");
        reqObj.setDate(dateStr);
        reqObj.setTime(timeStr);
        reqObj.setType(typeStr);

        String plg_id = String.valueOf(reqObj.getPledgeID());

        reffRequest.child(plg_id).setValue(reqObj);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewObj=inflater.inflate(R.layout.fragment_new_pledge, container, false);

        initialize();
        return viewObj;
    }

}