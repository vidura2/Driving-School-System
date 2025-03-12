<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/styles.css">
    <script src="../js/scripts.js"></script>
</head>
<body>
    <div class="container">
        <div class="card">
            <h1>Login</h1>
            <form action="../login" method="post" onsubmit="return validateForm()">
                <label>Email</label>
                <input type="email" name="email" required>
                <label>Password</label>
                <input type="password" name="password" required>
                <input type="submit" value="Login">
            </form>
            <% if (request.getParameter("error") != null) { %>
                <p class="error"><%= request.getParameter("error") %></p>
            <% } %>
            <p>Don't have an account? <a href="register.jsp">Register</a></p>
        </div>
    </div>
</body>
</html>