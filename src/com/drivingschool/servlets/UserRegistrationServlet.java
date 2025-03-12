package com.drivingschool.servlets;

import com.drivingschool.model.Student;
import com.drivingschool.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    private static final String USER_FILE = "data/users.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int id = new Random().nextInt(1000);
        Student student = new Student(id, name, email, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(student.toString());
            writer.newLine();
        }

        EmailUtil.sendEmail(email, "Welcome!", "You are registered, " + name + "!");
        response.sendRedirect("jsp/register.jsp?success=true");
    }
}