package com.example.studentsrecognitionandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reports extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports,container,false);



        Button btn = view.findViewById(R.id.view_list_button);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList();
            }
        });

        return view;

    }
    

    public void viewList(){
        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo mypageinfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page mypage = myPdfDocument.startPage(mypageinfo);

        Paint myPaint = new Paint();
        String myString = "Its just a trial and error today";
        int x = 10 ,y = 25;
        mypage.getCanvas().drawText(myString,x,y,myPaint);
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
}
