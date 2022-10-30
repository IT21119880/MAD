package com.android.paymentcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.omarshehe.forminputkotlin.FormInputButton;
import com.omarshehe.forminputkotlin.FormInputText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FormInputText name = findViewById(R.id.fullName);
//        name.setValue("sample text");
//
        FormInputText phoneNumber = findViewById(R.id.phoneNumber);
//        phoneNumber.setValue("077#####");
//
        FormInputText email = findViewById(R.id.email);
//        email.setValue("sample@gmail.com");

        Button continuebutton = findViewById(R.id.btnSubmit);
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getValue().toString().isEmpty() && !phoneNumber.getValue().toString().isEmpty() && !email.getValue().toString().isEmpty()){
                    Intent intent = new Intent(MainActivity.this, CardListActivity.class);
                    intent.putExtra("name", name.getValue().toString());
                    intent.putExtra("phoneNumber", phoneNumber.getValue().toString());
                    intent.putExtra("email", email.getValue().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}