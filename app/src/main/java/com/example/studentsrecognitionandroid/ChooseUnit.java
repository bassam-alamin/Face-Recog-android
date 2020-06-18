package com.example.studentsrecognitionandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseUnit extends Fragment {
    private Spinner spinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_unit,container,false);

        spinner = view.findViewById(R.id.spinner);
        List<String> units = new ArrayList<>();
        units.add(0,"choose Units");
        units.add(1,"computer science");
        units.add(2,"Biochemistry");
        units.add(3,"Acturial Science");
        units.add(4,"Agribusiness management");

        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,units);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("choose Units")){

                    // Do nothing
                }

                else{

                    String item = parent.getItemAtPosition(position).toString();


                    Toast.makeText(parent.getContext(),"You selected "+position,Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;

    }
}
