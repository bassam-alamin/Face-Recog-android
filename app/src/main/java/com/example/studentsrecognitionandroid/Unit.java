package com.example.studentsrecognitionandroid;

public class Unit {
    private int id;
    private String unit_title;
    private int unit_lecturer;

    public Unit(int id, String unit_title) {
        this.id = id;
        this.unit_title = unit_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit_title() {
        return unit_title;
    }

    public void setUnit_title(String unit_title) {
        this.unit_title = unit_title;
    }

    public int getUnit_lecturer() {
        return unit_lecturer;
    }

    public void setUnit_lecturer(int unit_lecturer) {
        this.unit_lecturer = unit_lecturer;
    }

    //to display object as a string in spinner
    @Override
    public String toString() {
        return unit_title;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Unit){
            Unit c = (Unit ) obj;
            if(c.getUnit_title().equals(unit_title) && c.getId()==id ) return true;
        }
        return false;
    }


}
