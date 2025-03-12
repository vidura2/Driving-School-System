<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.drivingschool.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <% 
            User admin = (User) session.getAttribute("user");
            if (admin == null || !"admin".equals(admin.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            User viewedUser = (User) request.getAttribute("viewedUser");
            if (viewedUser == null) {
                response.sendRedirect("adminDashboard.jsp");
                return;
            }
        %>
        <div class="card">
            <h1>User Details</h1>
            <p><strong>ID:</strong> <%= viewedUser.getId() %></p>
            <p><strong>Name:</strong> <%= viewedUser.getName() %></p>
            <p><strong>Email:</strong> <%= viewedUser.getEmail() %></p>
            <p><strong>Role:</strong> <%= viewedUser.getRole() %></p>
            <p><a href="adminDashboard.jsp">Back to Dashboard</a></p>
        </div>
    </div>
</body>
</html>