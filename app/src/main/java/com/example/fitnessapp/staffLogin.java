package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class staffLogin extends AppCompatActivity {
    TextView register;
    EditText editTextUsername, editTextPassword;
    Button loginbtn;
    FirebaseAuth auth;
    FloatingActionButton fab;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DataSnapshot dataSnapshot;
    DatabaseReference reference;
    String power_type ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        editTextUsername = findViewById(R.id.usernameLoginStaff);
        editTextPassword = findViewById(R.id.passwordLoginStaff);
        Toast.makeText(this, "Line 49", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "user:"+user, Toast.LENGTH_LONG).show();


        register = findViewById(R.id.loginRegister);
        loginbtn = findViewById(R.id.loginBtnStaff);
                /////////////////////////////////////////////








        ////////////////////////////////////////////////////
        Toast.makeText(this, "1st action listener initiated", Toast.LENGTH_SHORT).show();
        loginbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(staffLogin.this, "Going in first action listener check", Toast.LENGTH_SHORT).show();
                                           checkType();
                                            Toast.makeText(staffLogin.this, "after checking:"+power_type, Toast.LENGTH_SHORT).show();
//                                            if(power_type.equals("staff")){
//                                                signIn();
//                                            }
//                                            else{
//                                                Toast.makeText(staffLogin.this, "Access Denied!! Power type: "+ power_type, Toast.LENGTH_SHORT).show();
//                                            }
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


    

    public void signIn() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (username.isEmpty()) {
            editTextUsername.setError("Email is required");
            editTextUsername.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            editTextUsername.setError("Email pattern error");
            editTextUsername.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(), "Line executed", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    mAuth = FirebaseAuth.getInstance();
                    user = mAuth.getCurrentUser();
                    reference = FirebaseDatabase.getInstance().getReference().child("UserLists").child(user.getEmail().replace(".",","));


                    Toast.makeText(getApplicationContext(), "user:"+user, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(staffLogin.this, AdminLandingPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);}
                        else{
                        Toast.makeText(getApplicationContext(), "Access denied!! Access level: "+power_type , Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    public void checkType() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (username.isEmpty()) {
            editTextUsername.setError("Email is required");
            editTextUsername.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            editTextUsername.setError("Email pattern error");
            editTextUsername.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getApplicationContext(), "Line executed", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    mAuth = FirebaseAuth.getInstance();
                    user = mAuth.getCurrentUser();
                    reference = FirebaseDatabase.getInstance().getReference().child("UserLists").child(user.getEmail().replace(".",","));
                    Toast.makeText(staffLogin.this, "Got reference", Toast.LENGTH_SHORT).show();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Toast.makeText(staffLogin.this, "reading value from database", Toast.LENGTH_SHORT).show();
                            String userType = dataSnapshot.child("type").getValue().toString();
                            power_type = userType;
                            Toast.makeText(staffLogin.this, "PowerType: "+power_type, Toast.LENGTH_SHORT).show();
                            if(power_type.equals("staff")) {
                                signIn();
                            }
                            else{
                                Toast.makeText(staffLogin.this, "Access Denied", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Some Error", Toast.LENGTH_SHORT).show();
                        }
                    });



                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });



    }




}
