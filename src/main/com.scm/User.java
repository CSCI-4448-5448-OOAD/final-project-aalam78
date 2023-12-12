package com.scm

import java.util.Date;
import java.util.HashMap;

public class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private boolean loginStatus;
    private Date registerDate;
    private static HashMap<Integer, User> userMap;

    // Constructor
    public User(int userID, String userName, String email, String password, Date registerDate) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.loginStatus = false; // Default login status
        this.registerDate = registerDate;

        if (userMap == null) {
            userMap = new HashMap<>();
        }

        userMap.put(userID, this);
    }

    // Getter and setter methods
    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setUserPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setUserName(String newUserName) {
        this.userName = newUserName;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }

    public static User getUser(int userID) {
        return userMap.get(userID);
    }

    public static void addUsertoDB(User user) {
        userMap.put(user.getUserID(), user);
    }

    public static void removeUserFromDB(int userID) {
        userMap.remove(userID);
    }
