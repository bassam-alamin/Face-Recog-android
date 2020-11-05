package com.example.studentsrecognitionandroid;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reports extends Fragment {
    private JsonPlaceHolder jsonPlaceHolder;
    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports,container,false);

        LinearLayout layout = view.findViewById(R.id.report_card);







        ActivityCompat.requestPermissions(this.getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);


        sharedPreferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        int pk = sharedPreferences.getInt("id",0);

        retrofit = new Retrofit.Builder()
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
                LinearLayout layout = view.findViewById(R.id.report_card);

                List<ExaminationSession> e_session = response.body();

                for (ExaminationSession session: e_session){
                    Button btns = new Button(getContext());

                    btns.setText(session.getUnit()+" - "+session.getDepartment());
                    btns.setId(session.getId());
                    btns.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewList(session.getId());
                        }
                    });
                    layout.addView(btns);


                }

            }

            @Override
            public void onFailure(Call<List<ExaminationSession>> call, Throwable t) {

            }
        });


        return view;

    }
    

    public void viewList(int session_id){

        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<List<ReportsClass>> call = jsonPlaceHolder.getAttended(session_id);

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo mypageinfo = new PdfDocument.PageInfo.Builder(1200,2010,1).create();
        PdfDocument.Page mypage = myPdfDocument.startPage(mypageinfo);

        Paint myPaint = new Paint();
        Paint titlePaint = new Paint();

        int x = 1200 ,y = 25;
        Canvas canvas = mypage.getCanvas();

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        titlePaint.setTextSize(70);
        canvas.drawText("Egerton University",x/2,100,titlePaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        titlePaint.setTextSize(70);
        canvas.drawText("Examination Attendance List",x/2,200,titlePaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(35f);
        myPaint.setColor(Color.BLACK);
        canvas.drawText("Supervisor Name : Mr kimani Njoroge",20,300,myPaint);
        canvas.drawText("Unit Title : Computer Graphics",20,350,myPaint);
        canvas.drawText("Unit Code : Comp 490",20,400,myPaint);
        canvas.drawText("Department : Computer Science",20,450,myPaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        titlePaint.setTextSize(50);
        canvas.drawText("Attended",x/2,550,titlePaint);

        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20,600,x-20,680,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("No. ",40,650,myPaint);
        canvas.drawText("Student Name. ",200,650,myPaint);
        canvas.drawText("Reg No .",700,650,myPaint);

        canvas.drawLine(180,610,180,670,myPaint);
        canvas.drawLine(680,610,680,670,myPaint);
//        int jump = 0;
//
//        for (int i = 0; i <3;i++){
//            canvas.drawText(String.valueOf(1),40,770+jump,myPaint);
//            canvas.drawText("Bassam Alamini",200,770+jump,myPaint);
//            canvas.drawText("S13/15302/16",700,770+jump,myPaint);
//
//            jump+=100;
//
//
//        }



        call.enqueue(new Callback<List<ReportsClass>>() {
            @Override
            public void onResponse(Call<List<ReportsClass>> call, Response<List<ReportsClass>> response) {
                List<ReportsClass> reports = response.body();
                int jump = 0;
                int no = 1;

                for (ReportsClass r:reports){
                    canvas.drawText(String.valueOf(no),40,770+jump,myPaint);
                    canvas.drawText(r.getFirst_name() +" "+ r.getSecond_name(),200,770+jump,myPaint);
                    canvas.drawText(r.getReg_no(),700,770+jump,myPaint);
                    no+=1;
                    jump+=100;
                }

                Toast.makeText(getContext(),String.valueOf(reports.size()),Toast.LENGTH_SHORT).show();

                myPdfDocument.finishPage(mypage);

                String myFilePath = Environment.getExternalStorageDirectory().getPath() +"/comp490.pdf";
                File myFile = new File(myFilePath);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(myFile));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();

                }
                myPdfDocument.close();


                Toast.makeText(getContext(),"your pdf is ready",Toast.LENGTH_SHORT).show();


                }

            @Override
            public void onFailure(Call<List<ReportsClass>> call, Throwable t) {

            }
        });





    }
}
