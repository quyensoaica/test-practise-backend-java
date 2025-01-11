package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "Exams")
public class Exam {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "userId", length = 1000, nullable = false)
    private String userId;

    @Column(name = "examCode", length = 100, nullable = false)
    private String examCode;

    @Column(name = "startTime", length = 255, nullable = false)
    private String startTime;

    @Column(name = "endTime", length = 255, nullable = false)
    private String endTime;

    @Column(name = "isDeleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @Column(name = "isActive", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive = true;

    @Column(name = "isDone", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDone = false;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "createdBy", length = 255, nullable = true)
    private String createdBy;

    @Column(name = "updatedBy", length = 255, nullable = true)
    private String updatedBy;

    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER)
    private List<ExamQuestion> examQuestions;

    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER)
    private List<ExamSkillStatus> examSkillStatuses;

    // Getters and Setters

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
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public List<ExamSkillStatus> getExamSkillStatuses() {
        return examSkillStatuses;
    }

    public void setExamSkillStatuses(List<ExamSkillStatus> examSkillStatuses) {
        this.examSkillStatuses = examSkillStatuses;
    }

    // Constructors
    public Exam(String id, String userId, String examCode, String startTime, String endTime, boolean isDeleted, boolean isActive, boolean isDone, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
        this.id = id;
        this.userId = userId;
        this.examCode = examCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Exam() {
    }
}
