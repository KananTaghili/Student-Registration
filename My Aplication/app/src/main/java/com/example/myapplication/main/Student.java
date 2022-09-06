package com.example.myapplication.main;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String surname;
    public Integer age;
    public String className;
    public String id;

    public Student() {
        int i = 999 - Data.d.i;
        this.id = " " + i;
        Data.d.i++;
    }

    public void setData(String name, String surname, Integer age, String className) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.className = className;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id + "   " + this.name + "   " + this.surname + "   " + this.age + "   " + this.className;
    }
}