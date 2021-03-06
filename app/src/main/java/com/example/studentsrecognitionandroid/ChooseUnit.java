package com.example.studentsrecognitionandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private SharedPreferences sharedPreferences;
    private int unitid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_unit,container,false);



        sharedPreferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int pk = sharedPreferences.getInt("id",0);

        Retrofit retrofit = new Retrofit.Builder()
            //this is for local host when url is 127.0.0.0
            //  .baseUrl("http://10.0.2.2:8000/api/")
                .baseUrl("http://172.20.10.3:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<List<ExaminationSession>> call = jsonPlaceHolder.getExamSessions(pk);

        call.enqueue(new Callback<List<ExaminationSession>>() {
            @Override
            public void onResponse(Call<List<ExaminationSession>> call, Response<List<ExaminationSession>> response) {
                LinearLayout layout = view.findViewById(R.id.root_layout);

                List<ExaminationSession> e_session = response.body();

                for (ExaminationSession session: e_session){
                    Button btns = new Button(getContext());

                    btns.setText(session.getUnit()+" - "+session.getDepartment());
                    btns.setId(session.getId());
                    btns.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            opencamera(session.getId());
                        }
                    });
                    layout.addView(btns);


                }

            }

            @Override
            public void onFailure(Call<List<ExaminationSession>> call, Throwable t) {

            }
        });



//        spinner = view.findViewById(R.id.spinner);
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//            //this is for local host when url is 127.0.0.0
//            //  .baseUrl("http://10.0.2.2:8000/api/")
//                .baseUrl("http://172.20.10.3:8000/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
//
//
//        Call<List<Unit>> call = jsonPlaceHolder.getUnits();
//
//
//
//
//        call.enqueue(new Callback<List<Unit>>() {
//            @Override
//            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
//
//                if (!response.isSuccessful()){
//
//                }
//
//                List<Unit> units = response.body();
//                ArrayAdapter<Unit> arrayAdapter;
//
//                arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,units);
//
//                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(arrayAdapter);
//
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Unit unit = (Unit) parent.getSelectedItem();
//
//
//                        if (unit.getUnit_title().equals("choose unit")){
//
//                            // Do nothing
//                        }
//
//                        else{
//
////                    String item = parent.getItemAtPosition(position).toString();
//                            unitid = unit.getId();
//
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Unit>> call, Throwable t) {
//
//
//            }
//        });


//        Button btn = view.findViewById(R.id.proceed_button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                opencamera();
//            }
//        });

//        getExamsessions();

        return view;

    }

    public void getExamsessions(){
        sharedPreferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int pk = sharedPreferences.getInt("id",0);

        Call<List<ExaminationSession>> call = jsonPlaceHolder.getExamSessions(pk);

        call.enqueue(new Callback<List<ExaminationSession>>() {
            @Override
            public void onResponse(Call<List<ExaminationSession>> call, Response<List<ExaminationSession>> response) {
                List<ExaminationSession> e_session = response.body();




            }

            @Override
            public void onFailure(Call<List<ExaminationSession>> call, Throwable t) {

            }
        });



    }



    public void opencamera(int session_id){

        sharedPreferences = this.getActivity().getSharedPreferences("myprefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("session_id",session_id);
        editor.apply();

        Toast.makeText(getContext(),"You selected "+session_id,Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getActivity(),Camera2.class);

        startActivity(intent);

}
}
