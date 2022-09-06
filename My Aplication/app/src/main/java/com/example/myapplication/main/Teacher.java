package com.example.myapplication.main;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable {
    public String name;
    public String surname;
    public String username;
    public String password;
    public ArrayList<Student> student = new ArrayList<>();

    public Teacher(String name, String surname, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
