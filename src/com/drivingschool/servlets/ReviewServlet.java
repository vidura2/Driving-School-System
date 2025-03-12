package com.drivingschool.servlets;

import com.drivingschool.model.Review;
import com.drivingschool.model.Student;
import com.drivingschool.model.Instructor;
import com.drivingschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/submitReview")
public class ReviewServlet extends HttpServlet {
    private static final String REVIEW_FILE = "data/reviews.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        int instructorId = Integer.parseInt(request.getParameter("instructorId"));
        String comment = request.getParameter("comment");
        int rating = Integer.parseInt(request.getParameter("rating"));
        int reviewId = (int) (System.currentTimeMillis() % 100000);

        Student student = new Student(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        Instructor instructor = new Instructor(instructorId, "Instructor", "instructor@example.com", "pass");

        Review review = new Review(reviewId, student, instructor, comment, rating);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEW_FILE, true))) {
            writer.write(review.toString());
            writer.newLine();
        }

        response.sendRedirect("jsp/studentDashboard.jsp?reviewSubmitted=true");
    }
}