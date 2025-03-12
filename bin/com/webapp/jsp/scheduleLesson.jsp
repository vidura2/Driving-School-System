<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule Lesson</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <% 
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="card">
            <h1>Schedule a Lesson</h1>
            <form action="../scheduleLesson" method="post">
                <label>Instructor ID</label>
                <input type="number" name="instructorId" required>
                <label>Date & Time</label>
                <input type="datetime-local" name="dateTime" required>
                <input type="submit" value="Schedule">
            </form>
            <p><a href="studentDashboard.jsp">Back to Dashboard</a></p>
        </div>
    </div>
</body>
</html>