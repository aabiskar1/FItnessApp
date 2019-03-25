package com.example.fitnessapp;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    View layoutProfile;
    View profileIcon;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        layoutProfile = v.findViewById(R.id.profileLayoutH);
        profileIcon = v.findViewById(R.id.navigation_dashboard);
        layoutProfile.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.profileLayoutH):
                removeFragment();
                profileFragment();
        }
    }

    public void profileFragment() {
        removeFragment();
        ProfileFragment fragment = new ProfileFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();


    }

    public void homeFragment() {
        removeFragment();
        HomeFragment fragment = new HomeFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();

    }

    public void removeFragment() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }
    };

}
