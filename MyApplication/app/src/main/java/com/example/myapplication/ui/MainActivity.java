/*package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.se.omapi.Session;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.passwordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import java.internet.MimeMessge;
import java.net.PasswordAuthentication;
import java.util.Properties;


public class  MainActivity extends AppCompatActivity  {
    EditText _txtEmail, _txtMessage;
    Button _btnSend;
  //  private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_admin);
        FirebaseApp.initializeApp(this);
        _txtEmail=findViewById(R.id.txtEmail);
        _txtMessage=findViewById(R.id.txtMessage);
        _btnSend=findViewById(R.id.btnSend);
        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username="";
                final String password="";
                String messageToSend=_txtMessage.getText().toString();
                Properties props=new Properties();
                props.put("mail.smtp.auth","true");
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port","587");
                Session session=Session.getInstance(props,
                        new javax.mail.Authenticator(){
                    protected passwordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username, password);
                    }


            });
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.To, InternetAddress.parse(_txtEmail.getText().toString()));
            message.setSubject("sending email without opening gmail apps");
            message.setText(messageToSend);
            transport.send(message);
            toast.makeText(getApplicationContext(), text "email send successfully", Toast.LENGTH_LONG).show();
        }catch (MessagingException e){
            throw new RuntimeException(e);

        }
        }
        });
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().PermitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}*/
