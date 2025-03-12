package com.drivingschool.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role; // "student", "instructor", "admin"

    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + password + "," + role;
    }
}