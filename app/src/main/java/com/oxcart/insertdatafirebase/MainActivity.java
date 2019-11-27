package com.oxcart.insertdatafirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button submit, btnShowData;
    private EditText edtTextName, editTextPhoneNumber;

    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://insertdata-2ab8c.firebaseio.com/";

    // Declaring String variables to store name & phone number get from EditText.
    private String NameHolder, PhoneNumberHolder;
    Firebase firebase;
    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "Student_Detail_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(MainActivity.this);
        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        submit = findViewById(R.id.submit);
        btnShowData = findViewById(R.id.btn_show_data);
        edtTextName = findViewById(R.id.name);
        editTextPhoneNumber = findViewById(R.id.phone_number);

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowStudentDetailsActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDetails studentDetails = new StudentDetails();
                GetDataFromEditText();

                // Adding name into class function object.
                studentDetails.setName(NameHolder);

                // Adding phone number into class function object.
                studentDetails.setPhoneNumber(PhoneNumberHolder);

                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(StudentRecordIDFromServer).setValue(studentDetails);

                // Showing Toast message after successfully data submit.
                Toast.makeText(MainActivity.this,"Data Successfully into Firebase Database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GetDataFromEditText(){
        NameHolder = edtTextName.getText().toString();
        PhoneNumberHolder = editTextPhoneNumber.getText().toString();
    }
}
