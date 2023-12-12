package com.scm;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;


public class User {
    // Attributes
    private int userID;
    private String userName;
    private String email;
    private String password;
    private boolean loginStatus;
    private Date registerDate;

    // Static HashMap to store users with their IDs
    private static HashMap<Integer, User> userMap = new HashMap<>();

    // Constructors
    public User(int userID, String userName, String email, String password, Date registerDate) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.loginStatus = false; // Default login status
        this.registerDate = registerDate;


        // Add the user to the userMap
        userMap.put(userID, this);
    }

    // Methods
    public static User getUser(int userID) {
        return userMap.get(userID);
    }

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

    public static void addUserToDB(User user) {
        userMap.put(user.getUserID(), user);
    }

    public static void removeUserFromDB(int userID) {
        userMap.remove(userID);

        
    }

    // toString method
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", loginStatus=" + loginStatus +
                ", registerDate=" + dateFormat.format(registerDate) +
                '}';
    }

    public Object getPassword() {
        return null;
    }

}
