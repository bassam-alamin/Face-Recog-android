package com.example.studentsrecognitionandroid;

public class Users {
    private int id;
    private String username;
    private String password;
    private String email;
    private Boolean is_student;
    private Boolean is_lecturer;

    public Users( String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_student() {
        return is_student;
    }

    public void setIs_student(Boolean is_student) {
        this.is_student = is_student;
    }

    public Boolean getIs_lecturer() {
        return is_lecturer;
    }

    public void setIs_lecturer(Boolean is_lecturer) {
        this.is_lecturer = is_lecturer;
    }
}
