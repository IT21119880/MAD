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

public class ActivityDoctorDelete extends AppCompatActivity {

    private EditText dltDoctorname;
    private Button BtnDelete ;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_delete);

        dltDoctorname = (EditText) findViewById(R.id.docnamedelete);
        BtnDelete=(Button)findViewById(R.id.docbtndelete) ;


        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doctorname = dltDoctorname.getText().toString();
                if (!doctorname.isEmpty()){

                    deleteData(doctorname);

                }else{

                    Toast.makeText(ActivityDoctorDelete.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void deleteData(String username) {

        reference = FirebaseDatabase.getInstance().getReference("Admin");
        reference.child("Doctors").child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(ActivityDoctorDelete.this,"Successfuly Deleted",Toast.LENGTH_SHORT).show();
                    dltDoctorname.setText("");


                }else {

                    Toast.makeText(ActivityDoctorDelete.this,"Failed",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
}