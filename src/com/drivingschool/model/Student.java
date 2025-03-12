package com.drivingschool.model;

public class Student extends User {
    private int lessonsCompleted;

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password, "student");
        this.lessonsCompleted = 0;
    }

    public int getLessonsCompleted() { return lessonsCompleted; }
    public void setLessonsCompleted(int lessonsCompleted) { this.lessonsCompleted = lessonsCompleted; }
}