package com.practise.test.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String gender;
    private String avatar;

    public UserDTO(Long id, String username, String password, String gender, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
