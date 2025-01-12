package com.practise.test.dto.Feedback;

public class ISendFeedbackDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String feedback;

    // Constructors
    public ISendFeedbackDTO() {}

    public ISendFeedbackDTO(String fullName, String email, String phoneNumber, String feedback) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.feedback = feedback;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

