package com.example.studentsrecognitionandroid;

import android.content.Context;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseUnit extends Fragment {
    private Spinner spinner;
    private JsonPlaceHolder jsonPlaceHolder;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_unit,container,false);

        spinner = view.findViewById(R.id.spinner);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);



        Call<List<Unit>> call = jsonPlaceHolder.getUnits();


        call.enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {

                if (!response.isSuccessful()){

                }



                List<Unit> units = response.body();
                ArrayAdapter<Unit> arrayAdapter;

                arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,units);

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



            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {


            }
        });


//        List<Unit> units = new ArrayList<>();
//        units.add(new Unit(0,"choose unit"));
//        units.add(new Unit(32,"Computer science"));
//        units.add(new Unit(23,"Biochemistry"));
//        units.add(new Unit(1,"Acturial science"));
//        units.add(new Unit(34,"Agribusiness Management"));

//        ArrayAdapter<Unit> arrayAdapter;
//
//        arrayAdapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_spinner_item,units);
//
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Unit unit = (Unit) parent.getSelectedItem();
//
//
//                if (unit.getUnit_title().equals("choose unit")){
//
//                    // Do nothing
//                }
//
//                else{
//
////                    String item = parent.getItemAtPosition(position).toString();
//
//                    Toast.makeText(parent.getContext(),"You selected "+unit.getId(),Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        return view;

    }
}
