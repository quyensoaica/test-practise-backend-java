package com.practise.test.model.authorization;

public class TokenPayload {
    private String userId;
    private String username;
    private String role;
    private String roleName;

    public TokenPayload() {
    }

    public TokenPayload(String userId, String username, String role, String roleName) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.roleName = roleName;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
