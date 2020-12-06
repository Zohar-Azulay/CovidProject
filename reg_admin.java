package coronaAdmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.coronavolunteer.DAL;
import com.example.coronavolunteer.R;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class reg_admin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
            public void insert (String input){

                input=" INSERT INTO Users(Full_Name,Password,Phone_Number,City,Languages,Year_Of_Birth,Own_A_Car,Volunteer) VALUES(?,?,?,?,?,?,?,?)";
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
            public void Spinner(String Cities){
                try (Connection conn = DAL.connect();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(Cities)) {
                    Cities="SELECT City FROM Cities" ;
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }
}