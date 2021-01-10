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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class new_pledge extends Fragment {


    private View viewObj;

    private EditText day, month, year, hour, minute;
    private Spinner type;
    private Button confirm_pledge;
    private DatabaseReference reffSpinnerType, reffRequest; //= FirebaseDatabase.getInstance().getReference().child("");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss");
    private ArrayList<String> typeArrayList = new ArrayList<>();
    private String uid, openStr, time;
    private Date openDateTime, date;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String cDay, cMonth, cYear, cHour, cMinute, cSecond, startTime;

    private Requests reqObj;

    public new_pledge(){
        // Empty constractor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewObj = inflater.inflate(R.layout.fragment_new_pledge_details, container, false);
        initialize();
        return viewObj;
    }

    private void initialize(){
        try {
            reqObj = new Requests();
            confirm_pledge = viewObj.findViewById(R.id.btn_new_req_confirm);
            day = viewObj.findViewById(R.id.new_pledge_day);
            month = viewObj.findViewById(R.id.new_pledge_month);
            year = viewObj.findViewById(R.id.new_pledge_year);
            hour = viewObj.findViewById(R.id.new_pledge_hour);
            minute = viewObj.findViewById(R.id.new_pledge_minute);
            type = viewObj.findViewById(R.id.new_pledge_type_spinner);
            reffSpinnerType = FirebaseDatabase.getInstance().getReference().child("Req_types");
            reffRequest = FirebaseDatabase.getInstance().getReference().child("Requests");

            showDataTypeSpinner();

            confirm_pledge.setOnClickListener(v -> createNewPledge());
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showDataTypeSpinner() {
        reffSpinnerType.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //type
                typeArrayList.clear();
                for (DataSnapshot item : snapshot.getChildren())
                    typeArrayList.add(item.child("type").getValue(String.class));

                final ArrayAdapter<String> arrayAdapterType= new ArrayAdapter<String>(getActivity(),
                        R.layout.fragment_spinner_layout_item, typeArrayList);
                type.setAdapter(arrayAdapterType);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){
            }
        });
    }

    private void createNewPledge(){
        try{
            if (!day.getText().toString().isEmpty() && !month.getText().toString().isEmpty() &&
                    !year.getText().toString().isEmpty() && !hour.getText().toString().isEmpty() &&
                        !minute.getText().toString().isEmpty() &&
                            !type.getSelectedItem().toString().equals("")){
                if (mAuth!=null){
//                    confirm_pledge.setEnabled(false);
                    uid = mAuth.getCurrentUser().getUid();
                    updateDB();

                    Toast.makeText(getContext(), "בקשתך נקלטה בהצלחה במערכת!", Toast.LENGTH_LONG).show();
                    assert getFragmentManager() != null;
                    FragmentTransaction fr_newp = getFragmentManager().beginTransaction();
                    fr_newp.replace(R.id.fragment_pledger_container, new main_p());
                    fr_newp.commit();
                }
                else
                    Toast.makeText(getContext(), "mAuth == NULL", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "יש להשלים את כל הפרטים", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDB() throws ParseException {

        openDateTime = Calendar.getInstance().getTime();

        String dayStr = day.getText().toString().trim();
        String monthStr = month.getText().toString().trim();
        String yearStr = year.getText().toString().trim();
        String hourStr = hour.getText().toString().trim();
        String minuteStr = minute.getText().toString().trim();
        String typeStr = type.getSelectedItem().toString();

        date = new Date(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
        time = hourStr + ':' + minuteStr;

        reqObj.setPledger_uid(uid);
        reqObj.setHelper_uid(null);
        reqObj.setDate(date);
        reqObj.setTime(time);
        reqObj.setType(typeStr);

        cDay = String.valueOf(openDateTime.getDay());
        cMonth = String.valueOf(openDateTime.getMonth());
        cYear = String.valueOf(openDateTime.getYear());
        cHour = String.valueOf(openDateTime.getHours());
        cMinute = String.valueOf(openDateTime.getMinutes());
        cSecond = String.valueOf(openDateTime.getSeconds());

        startTime = cYear +  cMonth + cDay + cHour +  cMinute + cSecond;
        reqObj.setStartTime(startTime);
        reffRequest.child(startTime).setValue(reqObj);

//        reffRequest.child(uid).child(startTime).setValue(reqObj);
    }

}