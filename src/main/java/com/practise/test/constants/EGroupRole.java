package com.practise.test.constants;

public enum EGroupRole {
    ADMIN("1"),
    EXAMINER("2"),
    CONTESTANT("3");

    private String role;

    void Role(String role) {
        this.role = role;
    }

    EGroupRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
