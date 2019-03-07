package com.example.fitnessapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
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

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        layoutProfile = v.findViewById(R.id.profileLayoutH);
        layoutProfile.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.profileLayoutH):

                ProfileFragment fragment = new ProfileFragment();
                getFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
                Toast.makeText(getView().getContext(), "Profile fragment clicked", Toast.LENGTH_SHORT).show();
            case (R.id.homeProfileIcon):
                ProfileFragment fragmenta = new ProfileFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, fragmenta)
                        .commit();
                Toast.makeText(getView().getContext(), "Home fragment clicked", Toast.LENGTH_SHORT).show();
        }
    }

}
