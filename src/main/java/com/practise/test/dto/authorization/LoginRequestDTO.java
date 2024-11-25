package com.practise.test.dto.authorization;

public class LoginRequestDTO {
    private String username;
    private String password;
    private boolean isRememberMe;

    public boolean isRememberMe() {
        return isRememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        isRememberMe = rememberMe;
    }

    public LoginRequestDTO(String username, String password, boolean isRememberMe) {
        this.username = username;
        this.password = password;
        this.isRememberMe = isRememberMe;
    }

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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
