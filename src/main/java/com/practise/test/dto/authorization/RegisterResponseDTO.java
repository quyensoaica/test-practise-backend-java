package com.practise.test.dto.authorization;

public class RegisterResponseDTO {
    private String username;
    private String fullName;

    public RegisterResponseDTO() {
    }

    public RegisterResponseDTO(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return "RegisterResponseDTO{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
