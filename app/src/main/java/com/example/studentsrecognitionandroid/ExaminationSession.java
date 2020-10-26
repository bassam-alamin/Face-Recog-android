package com.example.studentsrecognitionandroid;

public class ExaminationSession {
    private int id;
    private String unit;
    private String department;

    public int getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
