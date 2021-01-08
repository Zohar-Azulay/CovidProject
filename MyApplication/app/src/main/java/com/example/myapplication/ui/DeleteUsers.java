package com.example.myapplication.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class DeleteUsers extends AppCompatActivity {

    private String id = "";

    private ListView delList;

    private DatabaseReference reff;

    private ArrayList<UserDB> arrayList = new ArrayList<UserDB>();
    private ArrayAdapter<UserDB> adapter;

    private String selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delete_users);

        if(getIntent().hasExtra("id"))
            id = getIntent().getStringExtra(id);

        delList = (ListView) findViewById(R.id.delList);

        reff =  FirebaseDatabase.getInstance().getReference("משתמשים");
        adapter =  new ArrayAdapter<UserDB>(this,android.R.layout.simple_list_item_1,arrayList);

        fillListView();
        delList.setClickable(true);
        delList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = (int) delList.getItemIdAtPosition(position);
                UserDB selectedUser = arrayList.get(index);
                selectedID = selectedUser.getUserID();

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUsers.this);
                builder.setTitle("מחיקה");
                builder.setMessage("האם אתה בטוח שברצונך למחוק את המשתמש בעל ה-id?" + selectedID);
                builder.setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reff.orderByChild("userID").equalTo(selectedID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot item: snapshot.getChildren())
                                    item.getRef().removeValue();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }

                });

                builder.setNegativeButton("לא", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void fillListView(){
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userInfo = "";
                UserDB user = new UserDB();
                for(DataSnapshot ds : snapshot.getChildren()){
                    user = new UserDB();
                    user.setName(ds.child("name").getValue(String.class));
                    user.setEmail(ds.child("email").getValue(String.class));
                    user.setPhone(ds.child("phone").getValue(String.class));
                    user.setUserID(ds.child("userID").getValue(String.class) );
                    //userInfo = "\n " + ds.child("name").getValue(String.class) + "\n " + ds.child("email").getValue(String.class) + "\n " + ds.child("phone").getValue(String.class) + "\n " + ds.child("userID").getValue(String.class) + "\n ";
                    arrayList.add(user);
                }
                delList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}