package com.drivingschool.servlets;

import com.drivingschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String USER_FILE = "data/users.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authenticateUser(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("jsp/adminDashboard.jsp");
            } else {
                response.sendRedirect("jsp/studentDashboard.jsp");
            }
        } else {
            response.sendRedirect("jsp/login.jsp?error=Invalid credentials");
        }
    }

    private User authenticateUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[2].equals(email) && parts[3].equals(password)) {
                    return new User(Integer.parseInt(parts[0]), parts[1], email, password, parts[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}