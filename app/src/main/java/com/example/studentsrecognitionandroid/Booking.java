package com.example.studentsrecognitionandroid;

public class Booking {
    private int id;
    private int student_name;
    private int unit_booked;
    private boolean is_attended;

    public Booking(int id) {
        this.id = id;
    }

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

    public int getUnit_booked() {
        return unit_booked;
    }

    public void setUnit_booked(int unit_booked) {
        this.unit_booked = unit_booked;
    }

    public boolean isIs_attended() {
        return is_attended;
    }

    public void setIs_attended(boolean is_attended) {
        this.is_attended = is_attended;
    }
}
