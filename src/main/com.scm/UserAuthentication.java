package com.scm

public class UserAuthentication {
    private static UserAuthentication instance;
    private User loggedInUser;

    // Private constructor to prevent instantiation
    private UserAuthentication() {
        // Initialization logic, if needed
    }

    // Public method to access the singleton instance
    public static UserAuthentication getInstance() {
        if (instance == null) {
            // Create the instance only if it doesn't exist
            instance = new UserAuthentication();
        }
        return instance;
    }

    // Method for user login
    public boolean login(String username, String password) {
        // Placeholder logic for user authentication
        // Replace with actual authentication logic
        if ("john_doe".equals(username) && "password123".equals(password)) {
            loggedInUser = new User(username);
            return true;
        }
        return false;
    }

    // Method for user logout
    public void logout() {
        loggedInUser = null;
    }

    // Method to get the currently logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }

    // Method to check if a user is authenticated
    public boolean isAuthenticated() {
        return loggedInUser != null;
    }

