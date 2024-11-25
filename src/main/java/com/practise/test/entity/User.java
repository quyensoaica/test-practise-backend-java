package com.practise.test.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "email", length = 255, nullable = true)
    private String email;

    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "fullName", length = 1000, nullable = false)
    private String fullName;

    @Column(name = "groupRoleId", length = 255, nullable = false)
    private String groupRoleId;

    @Column(name = "phoneNumber", length = 15, nullable = true)
    private String phoneNumber;

    @Column(name = "birthday", nullable = true)
    private String birthday;

    @Column(name = "gender", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'M'")
    private String gender = "M"; // Default value

    @Column(name = "avatar", length = 1000, nullable = true)
    private String avatar;

    @Column(name = "banner", length = 1000, nullable = true)
    private String banner;

    @Column(name = "isBlocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isBlocked = false;

    @Column(name = "isDeleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @Column(name = "isUpdated", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isUpdated = false;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "groupRoleId", referencedColumnName = "id", insertable = false, updatable = false)
    private GroupRole groupRole;

    public User() {
    }

    public User(String id, String email, String username, String password, String fullName, String groupRoleId, String phoneNumber, String birthday, String gender, String avatar, String banner, boolean isBlocked, boolean isDeleted, boolean isUpdated) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.groupRoleId = groupRoleId;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.gender = gender;
        this.avatar = avatar;
        this.banner = banner;
        this.isBlocked = isBlocked;
        this.isDeleted = isDeleted;
        this.isUpdated = isUpdated;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GroupRole getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
    }
}