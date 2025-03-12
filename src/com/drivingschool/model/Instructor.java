package com.drivingschool.model;

public class Instructor extends User {
    private int lessonsTaught;

    public Instructor(int id, String name, String email, String password) {
        super(id, name, email, password, "instructor");
        this.lessonsTaught = 0;
    }

    public int getLessonsTaught() { return lessonsTaught; }
    public void setLessonsTaught(int lessonsTaught) { this.lessonsTaught = lessonsTaught; }
}