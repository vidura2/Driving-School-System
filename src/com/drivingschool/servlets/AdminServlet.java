package com.drivingschool.servlets;

import com.drivingschool.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final String USER_FILE = "data/users.txt";
    private static final String LESSON_FILE = "data/lessons.txt";
    private static final String REVIEW_FILE = "data/reviews.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("deleteUser".equals(action)) {
            deleteUser(request.getParameter("userId"));
            response.sendRedirect("jsp/adminDashboard.jsp?message=User deleted");
        } else if ("deleteLesson".equals(action)) {
            deleteLesson(request.getParameter("lessonId"));
            response.sendRedirect("jsp/adminDashboard.jsp?message=Lesson deleted");
        } else if ("deleteReview".equals(action)) {
            deleteReview(request.getParameter("reviewId"));
            response.sendRedirect("jsp/adminDashboard.jsp?message=Review deleted");
        } else if ("approveLesson".equals(action)) {
            updateLessonStatus(request.getParameter("lessonId"), "approved");
            response.sendRedirect("jsp/adminDashboard.jsp?message=Lesson approved");
        } else if ("rejectLesson".equals(action)) {
            updateLessonStatus(request.getParameter("lessonId"), "rejected");
            response.sendRedirect("jsp/adminDashboard.jsp?message=Lesson rejected");
        } else if ("viewUser".equals(action)) {
            User viewedUser = getUserById(request.getParameter("userId"));
            request.setAttribute("viewedUser", viewedUser);
            request.getRequestDispatcher("jsp/userDetails.jsp").forward(request, response);
        } else {
            request.setAttribute("users", getAllUsers());
            request.setAttribute("lessons", getAllLessons());
            request.setAttribute("reviews", getAllReviews());
            request.getRequestDispatcher("jsp/adminDashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("addUser".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            int id = new Random().nextInt(1000);

            User newUser = new User(id, name, email, password, role);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
                writer.write(newUser.toString());
                writer.newLine();
            }
            response.sendRedirect("jsp/adminDashboard.jsp?message=User added");
        }
    }

    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    users.add(new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User getUserById(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equals(userId)) {
                    return new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getAllLessons() {
        List<String> lessons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LESSON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lessons.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    private List<String> getAllReviews() {
        List<String> reviews = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEW_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                reviews.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    private void deleteUser(String userId) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(userId + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(USER_FILE, lines);
    }

    private void deleteLesson(String lessonId) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LESSON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(lessonId + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(LESSON_FILE, lines);
    }

    private void deleteReview(String reviewId) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEW_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(reviewId + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(REVIEW_FILE, lines);
    }

    private void updateLessonStatus(String lessonId, String status) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LESSON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(lessonId + ",")) {
                    String[] parts = line.split(",");
                    lines.add(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + status);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(LESSON_FILE, lines);
    }

    private void writeToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}