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

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserReport extends AppCompatActivity {
    EditText userEmail;
    Button getUserDataBtn;

    DataSnapshot dataSnapshot;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth auth;
    String subPath;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FirebaseApp.initializeApp(this);




        Toast.makeText(this, "path: "+reference, Toast.LENGTH_SHORT).show();




        getUserDataBtn = findViewById(R.id.getUserDetailsAdminBtn);

        getUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
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


    public void getUserData(){
        EditText emailTxt = findViewById(R.id.emailUserDetailsEditText);
        String pathEmail= emailTxt.getText().toString().replace(".",",");
        Toast.makeText(this, pathEmail, Toast.LENGTH_LONG).show();
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("UserLists").child(pathEmail);
        String pathinString = myRef.toString();
        Toast.makeText(this, pathinString, Toast.LENGTH_LONG).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final TextView emailtxt = (TextView) findViewById(R.id.getEmail);
                final TextView nametxt = (TextView) findViewById(R.id.nametext);
                final TextView agetxt = (TextView) findViewById(R.id.getAge);
                final TextView nametxt2 = (TextView) findViewById(R.id.getName);
                final TextView reg_date = (TextView) findViewById(R.id.getRegDate);
                String name = dataSnapshot.child("fullname").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();

                nametxt.setText(email);
                nametxt2.setText(name);
                agetxt.setText(age);
                emailtxt.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
