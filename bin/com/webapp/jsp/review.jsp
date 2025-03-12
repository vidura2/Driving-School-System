<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Submit Review</title>
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
            <h1>Submit a Review</h1>
            <form action="../submitReview" method="post">
                <label>Instructor ID</label>
                <input type="number" name="instructorId" required>
                <label>Comment</label>
                <textarea name="comment" required></textarea>
                <label>Rating (1-5)</label>
                <input type="number" name="rating" min="1" max="5" required>
                <input type="submit" value="Submit">
            </form>
            <p><a href="studentDashboard.jsp">Back to Dashboard</a></p>
        </div>
    </div>
</body>
</html>