package com.example.studentsrecognitionandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        sharedPreferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);



        int value = sharedPreferences.getInt("id",0);

        String em = sharedPreferences.getString("email",null);
        String username = sharedPreferences.getString("username",null);



        TextView welcome = view.findViewById(R.id.welcome_lecturer);
//
        welcome.setText("Welcome "+username);

        return view;
    }
}
