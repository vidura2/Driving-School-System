<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.drivingschool.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <div class="container">
        <% 
            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <div class="card">
            <h1>Admin Dashboard</h1>
            <p>Welcome, <%= user.getName() %>! | <a href="../logout">Logout</a></p>

            <h2>Add New User</h2>
            <form action="../admin" method="post">
                <input type="hidden" name="action" value="addUser">
                <label>Name</label>
                <input type="text" name="name" required>
                <label>Email</label>
                <input type="email" name="email" required>
                <label>Password</label>
                <input type="password" name="password" required>
                <label>Role</label>
                <select name="role">
                    <option value="student">Student</option>
                    <option value="instructor">Instructor</option>
                </select>
                <input type="submit" value="Add User">
            </form>

            <h2>Users</h2>
            <table>
                <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Action</th></tr>
                <% 
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User u : users) {
                %>
                    <tr>
                        <td><%= u.getId() %></td>
                        <td><%= u.getName() %></td>
                        <td><%= u.getEmail() %></td>
                        <td><%= u.getRole() %></td>
                        <td>
                            <a href="../admin?action=viewUser&userId=<%= u.getId() %>">View</a> |
                            <a href="../admin?action=deleteUser&userId=<%= u.getId() %>">Delete</a>
                        </td>
                    </tr>
                <% 
                        }
                    }
                %>
            </table>

            <h2>Lessons</h2>
            <table>
                <tr><th>Lesson ID</th><th>Student ID</th><th>Instructor ID</th><th>DateTime</th><th>Status</th><th>Action</th></tr>
                <% 
                    List<String> lessons = (List<String>) request.getAttribute("lessons");
                    if (lessons != null) {
                        for (String lesson : lessons) {
                            String[] parts = lesson.split(",");
                %>
                    <tr>
                        <td><%= parts[0] %></td>
                        <td><%= parts[1] %></td>
                        <td><%= parts[2] %></td>
                        <td><%= parts[3] %></td>
                        <td><%= parts[4] %></td>
                        <td>
                            <% if ("pending".equals(pars[4])) { %>
                                <a href="../admin?action=approveLesson&lessonId=<%= parts[0] %>">Approve</a> |
                                <a href="../admin?action=rejectLesson&lessonId=<%= parts[0] %>">Reject</a> |
                            <% } %>
                            <a href="../admin?action=deleteLesson&lessonId=<%= parts[0] %>">Delete</a>
                        </td>
                    </tr>
                <% 
                        }
                    }
                %>
            </table>

            <h2>Reviews</h2>
            <table>
                <tr><th>Review ID</th><th>Student ID</th><th>Instructor ID</th><th>Comment</th><th>Rating</th><th>Action</th></tr>
                <% 
                    List<String> reviews = (List<String>) request.getAttribute("reviews");
                    if (reviews != null) {
                        for (String review : reviews) {
                            String[] parts = review.split(",");
                %>
                    <tr>
                        <td><%= parts[0] %></td>
                        <td><%= parts[1] %></td>
                        <td><%= parts[2] %></td>
                        <td><%= parts[3] %></td>
                        <td><%= parts[4] %></td>
                        <td><a href="../admin?action=deleteReview&reviewId=<%= parts[0] %>">Delete</a></td>
                    </tr>
                <% 
                        }
                    }
                %>
            </table>

            <% if (request.getParameter("message") != null) { %>
                <p class="success"><%= request.getParameter("message") %></p>
            <% } %>
        </div>
    </div>
</body>
</html>