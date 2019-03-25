package com.example.fitnessapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    DataSnapshot dataSnapshot;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth auth;
    String subPath;





    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /** Below Code handel operation to show the name or other data from the FireBase real time database and shows it on the profile fragment*/
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("UserLists").child(user.getEmail().replace(".",","));
        final TextView nametxt = (TextView) v.findViewById(R.id.nametext);
        final TextView agetxt = (TextView) v.findViewById(R.id.getAge);
        final TextView emailtxt = (TextView) v.findViewById(R.id.getEmail);
        final TextView nametxt2 = (TextView) v.findViewById(R.id.getName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("fullname").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                nametxt.setText(name);
                nametxt2.setText(name);
                agetxt.setText(age);
                emailtxt.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /** Above Code handel operation to show the name or other data from the FireBase real time database and shows it on the profile fragment*/

        return v;

    }



}
