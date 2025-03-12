<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../css/styles.css">
    <script src="../js/scripts.js"></script>
</head>
<body>
    <div class="container">
        <div class="card">
            <h1>Register</h1>
            <form action="../register" method="post" onsubmit="return validateForm()">
                <label>Name</label>
                <input type="text" name="name" required>
                <label>Email</label>
                <input type="email" name="email" required>
                <label>Password</label>
                <input type="password" name="password" required>
                <input type="submit" value="Register">
            </form>
            <% if (request.getParameter("success") != null) { %>
                <p class="success">Registration successful! <a href="login.jsp">Login</a></p>
            <% } %>
        </div>
    </div>
</body>
</html>