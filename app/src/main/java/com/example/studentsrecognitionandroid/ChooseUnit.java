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
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(0,"choose unit"));
        units.add(new Unit(32,"Computer science"));
        units.add(new Unit(23,"Biochemistry"));
        units.add(new Unit(1,"Acturial science"));
        units.add(new Unit(34,"Agribusiness Management"));

        ArrayAdapter<Unit> arrayAdapter;

        arrayAdapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,units);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = (Unit) parent.getSelectedItem();


                if (unit.getUnit_title().equals("choose unit")){

                    // Do nothing
                }

                else{

//                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(),"You selected "+unit.getId(),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;

    }
}
