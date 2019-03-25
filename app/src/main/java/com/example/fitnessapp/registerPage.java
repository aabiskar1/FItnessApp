package com.example.fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class registerPage extends AppCompatActivity {
    Button registerBtn;
    String gender,type;
    TextView login;
    EditText txt_fullName, txt_age, txt_email, txt_password;
    EditText editTextEmail;
    RadioButton radioGenderMale, radioGenderFemale;
    DatabaseReference registrationdetails;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.registerLogin);
        txt_fullName = findViewById(R.id.userName);
        txt_age = findViewById(R.id.userAge);
        txt_email = findViewById(R.id.userEmail);
        txt_password = findViewById(R.id.userPassword);
        radioGenderMale = findViewById(R.id.radioMale);
        radioGenderFemale = findViewById(R.id.radioFemale);
        registerBtn = findViewById(R.id.userRegisterBtn);
        registrationdetails = FirebaseDatabase.getInstance().getReference("UserLists");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            registerUser();
            }
        });


    }

    public void registerUser(){
        final String reg_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        final String fullname = txt_fullName.getText().toString().trim();
        final String age = txt_age.getText().toString().trim().toLowerCase();
        final String email = txt_email.getText().toString().trim().toLowerCase();
        final String type = "user";
        final String password = txt_password.getText().toString().trim();

        if (radioGenderMale.isSelected()) {
            gender = "Male";
            return;
        }
        if (radioGenderFemale.isSelected()) {
            gender = "Female";
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(registerPage.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            txt_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(registerPage.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            txt_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(registerPage.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            txt_fullName.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(registerPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(registerPage.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                            registrationdetails registrationDetails = new registrationdetails(fullname,age,email,reg_date,type);
                            registrationdetails.child(email.replace(".",",")).setValue(registrationDetails);
                            startActivity(new Intent(getApplicationContext(), loginActivity.class));
                        }


                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG)
                                    .show();
                            //Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
}
