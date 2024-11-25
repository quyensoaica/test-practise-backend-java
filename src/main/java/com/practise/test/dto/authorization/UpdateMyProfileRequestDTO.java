package com.practise.test.dto.authorization;

public class UpdateMyProfileRequestDTO {
//    id: string;
//    avatar: string;
//    email: string;
//    fullName: string;
//    birthday: string;
//    gerder: EGenderStatus;
//    phoneNumber: string;
    private String id;
    private String avatar;
    private String email;
    private String fullName;
    private String birthday;
    private String gender;
    private String phoneNumber;

    public UpdateMyProfileRequestDTO(String id, String avatar, String email, String fullName, String birthday, String gender, String phoneNumber) {
        this.id = id;
        this.avatar = avatar;
        this.email = email;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public UpdateMyProfileRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
