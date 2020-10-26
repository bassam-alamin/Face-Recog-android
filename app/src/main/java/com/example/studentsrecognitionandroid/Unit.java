package com.example.studentsrecognitionandroid;

public class Unit {
    private Integer id;
    private String unit_title;

    public Integer getId() {
        return id;
    }

    public Unit(Integer id, String unit_title) {
        this.id = id;
        this.unit_title = unit_title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit_title() {
        return unit_title;
    }

    public void setUnit_title(String unit_title) {
        this.unit_title = unit_title;
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
