package com.example.studentsrecognitionandroid;

import android.media.Image;

public class Student {
    private int id;
    private int student_name;
    private String reg_no;
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_name() {
        return student_name;
    }

    public void setStudent_name(int student_name) {
        this.student_name = student_name;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
