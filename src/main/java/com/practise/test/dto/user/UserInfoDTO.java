package com.practise.test.dto.user;

import com.practise.test.entity.GroupRole;
import com.practise.test.model.role.GroupRoleInfo;

public class UserInfoDTO {
    private String id;
    private String username;
    private String fullName;
    private String groupRoleId;
    private String phoneNumber;
    private String email;
    private String birthDate;
    private String gender;
    private String avatar;
    private String banner;
    private boolean isBlocked;
    private boolean isUpdated;
    private GroupRoleInfo groupRole;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String id, String username, String fullName, String groupRoleId, String phoneNumber, String email, String birthDate, String gender, String avatar, String banner, boolean isBlocked, boolean isUpdated, GroupRoleInfo groupRole) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.groupRoleId = groupRoleId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.avatar = avatar;
        this.banner = banner;
        this.isBlocked = isBlocked;
        this.isUpdated = isUpdated;
        this.groupRole = groupRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGroupRoleId() {
        return groupRoleId;
    }

    public void setGroupRoleId(String groupRoleId) {
        this.groupRoleId = groupRoleId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public GroupRoleInfo getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(GroupRoleInfo groupRole) {
        this.groupRole = groupRole;
    }
}
