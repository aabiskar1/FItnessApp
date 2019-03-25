package com.example.fitnessapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateCustomer extends AppCompatActivity {

    Button updateUserDetailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        updateUserDetailBtn = findViewById(R.id.updateUserDetailsBtn);
        updateUserDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomerData();
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

    public void updateCustomerData(){
        final EditText emailTxt = findViewById(R.id.updateCustomerEmailID);
        String pathEmail= emailTxt.getText().toString().replace(".",",");
        Toast.makeText(this, pathEmail, Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("UserLists").child(pathEmail);
        String pathinString = myRef.toString();
        Toast.makeText(this, pathinString, Toast.LENGTH_LONG).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 final TextView emailtxt = (TextView) findViewById(R.id.updateGetEmail);
                 final TextView nametxt = (TextView) findViewById(R.id.updateGetName);
                 final TextView agetxt = (TextView) findViewById(R.id.updateGateAge);
                String nameS=nametxt.getText().toString().toLowerCase().trim();
                String emailS=emailtxt.getText().toString().toLowerCase().trim();
                String ageS=agetxt.getText().toString().toLowerCase().trim();
                myRef.child("fullname").setValue(nameS);
                myRef.child("email").setValue(emailS);
                myRef.child("age").setValue(ageS);
                Toast.makeText(updateCustomer.this, "Sucessfully updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
