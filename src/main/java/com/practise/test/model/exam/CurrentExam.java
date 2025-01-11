package com.practise.test.model.exam;

public class CurrentExam {
    private String id;
    private String userId;
    private String ExamCode;
    private String startTime;
    private String endTime;
    private boolean isDeleted;
    private boolean isActive;
    private boolean isDone;
    private String createdAt;
    private String updatedAt;

    public CurrentExam(String id, String userId, String examCode, String startTime, String endTime, boolean isDeleted, boolean isActive, boolean isDone, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.ExamCode = examCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CurrentExam() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExamCode() {
        return ExamCode;
    }

    public void setExamCode(String examCode) {
        ExamCode = examCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
