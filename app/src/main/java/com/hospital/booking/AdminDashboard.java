package com.hospital.booking;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hospital.booking.AdminHospital.AddHospitals;
import com.hospital.booking.crud.AppointmentList;
import com.hospital.booking.crud.CreateAppoinment;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboard extends AppCompatActivity {

    private List<CategoryModel> movieList = new ArrayList<>();
    private CategoriesAdapter mAdapter;
    private TabItem doctor,hospital,product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new CategoriesAdapter(movieList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();

        CardView addCard =  findViewById(R.id.card_add);
        CardView viewCard =  findViewById(R.id.card_view);
        CardView updateCard =  findViewById(R.id.update_card);
        CardView deletecard =  findViewById(R.id.card_delete);

        TabItem doctor=findViewById(R.id.bottomadminDoctor);
        TabItem hospital=findViewById(R.id.bottomadminhospital);
        TabItem product=findViewById(R.id.bottomadminproduct);

//        hospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(AdminDashboard.this, HospitalDashBoard.class);
//                startActivity(intent);
//
//            }
//        });




        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddChoose();
//                Intent intent = new Intent(AdminDashboard.this, CreateAppoinment.class);
//                startActivity(intent);

            }
        });
        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewChoose();
//                Intent intent = new Intent(AdminDashboard.this, AppointmentList.class);
//                startActivity(intent);

            }
        });
        updateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChoose();
//                Intent intent = new Intent(AdminDashboard.this, UpdateDoctors.class);
//                startActivity(intent);

            }
        });


        deletecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteChoose();
//                Intent intent = new Intent(AdminDashboard.this, ActivityDoctorDelete.class);
//                startActivity(intent);

            }
        });






    }





    private void prepareMovieData() {
        CategoryModel movie = new CategoryModel("All Datas", "10 Details ");
        movieList.add(movie);
        movie = new CategoryModel("View Details", "14 Details");
        movieList.add(movie);
        movie = new CategoryModel("Ups Details", "32 Details");
        movieList.add(movie);
        movie = new CategoryModel("Delete Details", "24 Details");
        movieList.add(movie);

    }


    private void AddChoose() {



        AlertDialog.Builder dialog = new AlertDialog.Builder(AdminDashboard.this);
        dialog.setTitle("Do You Really Want to Add Doctor ?? ");


        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(AdminDashboard.this, CreateAppoinment.class);
                startActivity(intent);


            }
        });



        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // progressbar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }


    private void ViewChoose() {



        AlertDialog.Builder dialog = new AlertDialog.Builder(AdminDashboard.this);
        dialog.setTitle("Do You Really Want to View  Doctor Details ?? ");


        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(AdminDashboard.this,AppointmentList .class);
                startActivity(intent);


            }
        });



        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // progressbar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    private void updateChoose() {



        AlertDialog.Builder dialog = new AlertDialog.Builder(AdminDashboard.this);
        dialog.setTitle("Do You Really Want to Update  Doctor Details ?? ");


        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(AdminDashboard.this,UpdateDoctors .class);
                startActivity(intent);


            }
        });



        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // progressbar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }


    private void DeleteChoose() {



        AlertDialog.Builder dialog = new AlertDialog.Builder(AdminDashboard.this);
        dialog.setTitle("Do You Really Want to Update  Doctor Details ?? ");


        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(AdminDashboard.this,ActivityDoctorDelete .class);
                startActivity(intent);


            }
        });



        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // progressbar.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }
}