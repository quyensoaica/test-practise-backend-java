package com.practise.test.model.user;

import com.practise.test.model.role.UserRole;

public class UserInfo {
    private String userId;
    private String username;
    private String fullName;
    private UserRole role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserInfo() {
    }

    public UserInfo(String userId, String username, String fullName, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }
}
