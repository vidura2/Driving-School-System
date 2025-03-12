package com.drivingschool.model;

public class Lesson {
    private int lessonId;
    private Student student;
    private Instructor instructor;
    private String dateTime;
    private String status; // "pending", "approved", "rejected"

    public Lesson(int lessonId, Student student, Instructor instructor, String dateTime, String status) {
        this.lessonId = lessonId;
        this.student = student;
        this.instructor = instructor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getLessonId() { return lessonId; }
    public Student getStudent() { return student; }
    public Instructor getInstructor() { return instructor; }
    public String getDateTime() { return dateTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return lessonId + "," + student.getId() + "," + instructor.getId() + "," + dateTime + "," + status;
    }
}