package com.android.paymentcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        TextView homePage = findViewById(R.id.homepage);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Redirect to home page
                Intent homePage = new Intent(PaymentSuccess.this, MainActivity.class);
                startActivity(homePage);
            }
        });


    }

}