package com.hospital.booking.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hospital.booking.R;

public class EditAppointment extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    FirebaseDatabase mFirebaseInstance;
    String appointmentId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        EditText name = (EditText) findViewById(R.id.docNameEdit);
        EditText address = (EditText) findViewById(R.id.docAuthEdit333);
        EditText phone = (EditText) findViewById(R.id.docAuthEdit33dd);
//        EditText time = (EditText) findViewById(R.id.docAuthEdit3);
        TextView date = (TextView) findViewById(R.id.docAuthEdit);
        TextView doctorname = (TextView) findViewById(R.id.docNameEdit2);

        Intent intent = getIntent();
        appointmentId = intent.getStringExtra("id");

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("Admin");
        mDatabaseReference.child("Doctors").child(appointmentId)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot.exists()){
                            AppointmentModel appointmentModel = dataSnapshot.getValue(AppointmentModel.class);
                            name.setText(appointmentModel.getdoctorEmail());
                            doctorname.setText(appointmentModel.getDoctorName());
                            date.setText(appointmentModel.getaddDate());
//                            time.setText(appointmentModel.getTime());
                            address.setText(appointmentModel.getdoctorAddress());
                            phone.setText(appointmentModel.getPhoneNo());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError){
                        // TODO: Implement this method
                    }

                });


        Button editDetails = findViewById(R.id.btnSubmit);
        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppointmentModel appointment = new AppointmentModel();
                appointment.setId(appointmentId);
                appointment.setdoctorEmail(name.getText().toString());
                appointment.setDoctorName(doctorname.getText().toString());
                appointment.setaddDate(date.getText().toString());
//                appointment.setTime(time.getText().toString());
                appointment.setPhoneNo(phone.getText().toString());
                appointment.setdoctorAddress(address.getText().toString());
                FirebaseDatabase.getInstance().getReference("Admin")
                        .child("Doctors").child(appointmentId).setValue(appointment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplication(), "Doctor edited successfully", Toast.LENGTH_LONG)
                                .show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplication(), "Doctor edit failed", Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });

        Button delete = findViewById(R.id.btndelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Admin")
                        .child("Doctors").child(appointmentId)
                        .removeValue().addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void mVoid){
                        Toast.makeText(getApplication(), "Doctor deleted successfully", Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                });
            }
        });
    }
}