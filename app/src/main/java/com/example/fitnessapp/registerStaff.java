package com.example.fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class registerStaff extends AppCompatActivity{
    Button registerBtn;
    private RadioGroup radioGroup;

    TextView login;
    EditText txt_fullName, txt_age, txt_email, txt_password;
    EditText editTextEmail;
    RadioButton radioGenderMale, radioGenderFemale;

    DatabaseReference registrationDetailsStaff;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_staff);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        mAuth = FirebaseAuth.getInstance();
        txt_fullName = findViewById(R.id.staffName);
        txt_age = findViewById(R.id.staffAge);
        txt_email = findViewById(R.id.staffEmail);
        txt_password = findViewById(R.id.staffPassword);
        radioGenderMale = findViewById(R.id.radioMaleStaff);
        radioGenderFemale = findViewById(R.id.radioFemaleStaff);
        registerBtn = findViewById(R.id.staffRegisterBtn);
        registrationDetailsStaff = FirebaseDatabase.getInstance().getReference("UserLists");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStaff();
            }
        });


    }
    public void registerStaff() {
        final String gender;


        final String fullname = txt_fullName.getText().toString().trim();
        final String age = txt_age.getText().toString().trim().toLowerCase();
        final String email = txt_email.getText().toString().trim().toLowerCase();
        final String password = txt_password.getText().toString().trim();
        final String reg_date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(registerStaff.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            txt_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(registerStaff.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            txt_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(registerStaff.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            txt_fullName.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(registerStaff.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            registrationDetailsStaff registrationdetailsStaff = new registrationDetailsStaff(fullname, age, email,reg_date,"staff");
                            registrationDetailsStaff.child(email.replace(".", ",")).setValue(registrationdetailsStaff);
                            Toast.makeText(registerStaff.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                        }


                    }
                    });


}
}
