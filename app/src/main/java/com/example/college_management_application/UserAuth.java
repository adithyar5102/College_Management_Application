package com.example.college_management_application;

public class UserAuth {
    private String username;
    private String password;

    // Empty constructor
    public UserAuth() {
    }

    // Constructor with username and password
    public UserAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and setter methods for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
