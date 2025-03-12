<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.drivingschool.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <% 
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="card">
            <h1>Welcome, <%= user.getName() %>!</h1>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Role:</strong> <%= user.getRole() %></p>
            <div>
                <a href="scheduleLesson.jsp">Schedule a Lesson</a> | 
                <a href="review.jsp">Submit a Review</a> | 
                <a href="../logout">Logout</a>
            </div>
            <% if (request.getParameter("lessonScheduled") != null) { %>
                <p class="success">Lesson scheduled successfully!</p>
            <% } %>
            <% if (request.getParameter("reviewSubmitted") != null) { %>
                <p class="success">Review submitted successfully!</p>
            <% } %>
        </div>
    </div>
</body>
</html>