package com.practise.test.model.authorization;

public class AccessToken {
    private String token;
    private String expiresAt;
    private String expiresAtUtc;

    public AccessToken() {
    }

    public AccessToken(String token, String expiresAt, String expiresAtUtc) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.expiresAtUtc = expiresAtUtc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getExpiresAtUtc() {
        return expiresAtUtc;
    }

    public void setExpiresAtUtc(String expiresAtUtc) {
        this.expiresAtUtc = expiresAtUtc;
    }
}
