package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminLandingPage extends AppCompatActivity {
    View registerStaff;
    View registerCustomer;
    View userReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerStaff = findViewById(R.id.registerStaffAdminLayout);
        registerCustomer = findViewById(R.id.registerCustomerAdminLayout);
        userReport = findViewById(R.id.customerReportAdminLayout);



        registerCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCustomerPage();
            }
        });
        userReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userReportPage();
            }
        });
        registerStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStaffPage();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void registerStaffPage(){
        Intent myIntent = new Intent(AdminLandingPage.this, registerStaff.class);
        startActivity(myIntent);
    }
    public void registerCustomerPage(){
        Intent myIntent = new Intent(AdminLandingPage.this, registerPage.class);
        startActivity(myIntent);
    }

    public void userReportPage(){
        Intent myIntent = new Intent(AdminLandingPage.this, UserReport.class);
        startActivity(myIntent);
    }


}
