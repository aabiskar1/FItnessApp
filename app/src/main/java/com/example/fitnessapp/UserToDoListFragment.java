package com.example.fitnessapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserToDoListFragment extends Fragment {
    EditText userToDOList;


    public UserToDoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_user_to_do_list, container, false);
       userToDOList = v.findViewById(R.id.userToDoEditText);
        return v;

    }



}
