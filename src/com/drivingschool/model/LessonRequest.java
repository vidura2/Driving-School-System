package com.drivingschool.model;

public class LessonRequest {
    private int requestId;
    private Student student;
    private String preferredDateTime;

    public LessonRequest(int requestId, Student student, String preferredDateTime) {
        this.requestId = requestId;
        this.student = student;
        this.preferredDateTime = preferredDateTime;
    }

    public int getRequestId() { return requestId; }
    public Student getStudent() { return student; }
    public String getPreferredDateTime() { return preferredDateTime; }
}