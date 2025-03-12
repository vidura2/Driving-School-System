package com.drivingschool.model;

public class Review {
    private int reviewId;
    private Student student;
    private Instructor instructor;
    private String comment;
    private int rating;

    public Review(int reviewId, Student student, Instructor instructor, String comment, int rating) {
        this.reviewId = reviewId;
        this.student = student;
        this.instructor = instructor;
        this.comment = comment;
        this.rating = rating;
    }

    public String toString() {
        return reviewId + "," + student.getId() + "," + instructor.getId() + "," + comment + "," + rating;
    }
}