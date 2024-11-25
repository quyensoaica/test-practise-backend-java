package com.practise.test.model.role;

public class UserRole {
    private String roleName;
    private String displayName;

    public UserRole() {
    }

    public UserRole(String roleName, String displayName) {
        this.roleName = roleName;
        this.displayName = displayName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
