package com.drivingschool.servlets;

import com.drivingschool.model.Lesson;
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

@WebServlet("/scheduleLesson")
public class LessonServlet extends HttpServlet {
    private static final String LESSON_FILE = "data/lessons.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String dateTime = request.getParameter("dateTime");
        int instructorId = Integer.parseInt(request.getParameter("instructorId"));
        int lessonId = (int) (System.currentTimeMillis() % 100000);

        Student student = new Student(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        Instructor instructor = new Instructor(instructorId, "Instructor", "instructor@example.com", "pass");

        Lesson lesson = new Lesson(lessonId, student, instructor, dateTime, "pending");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LESSON_FILE, true))) {
            writer.write(lesson.toString());
            writer.newLine();
        }

        response.sendRedirect("jsp/studentDashboard.jsp?lessonScheduled=true");
    }
}