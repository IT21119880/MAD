package com.hospital.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateDoctors extends AppCompatActivity {

    DatabaseReference databaseReference;
    private EditText editEmail, editNumber, editAddress, editDate,editDoctorname;
    private Button BtnSubmit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctors);


        editDoctorname = (EditText) findViewById(R.id.docname);
        editEmail = (EditText) findViewById(R.id.docemail);
        editNumber = (EditText) findViewById(R.id.docphone);
        editAddress = (EditText) findViewById(R.id.docaddress);
        editDate = (EditText) findViewById(R.id.docdate);
        BtnSubmit=(Button)findViewById(R.id.docbtnupdate) ;

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DocEmail = editEmail.getText().toString();
                String DocNumber = editNumber.getText().toString();
                String DocAddress = editAddress.getText().toString();
                String DocDate = editDate.getText().toString();
                String DocName = editDoctorname.getText().toString();

                updatedata(DocEmail,DocNumber,DocAddress,DocDate,DocName);

            }

        });

    }

    private void updatedata(String DocEmail, String DocNumber, String DocAddress, String DocDate , String DocName) {

        HashMap User = new HashMap();
//        User.put("firstName",DocName);
        User.put("doctorEmail",DocEmail);
        User.put("phneNo",DocNumber);
        User.put("doctorAddress",DocAddress);
        User.put("addDate",DocDate);
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
        databaseReference.child("Doctors").child(DocName).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){

//                   userName.setText("");
//                    firstName.setText("");
//                    lastname.setText("");
//                   age.setText("");
//                    Toast.makeText(UpdateData.this,"Successfully Updated",Toast.LENGTH_SHORT).show();

                }else {

//                    Toast.makeText(UpdateData.this,"Failed to Update",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }



}