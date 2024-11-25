package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Table(name = "Answers")
public class Answer {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "subQuestionId", length = 255, nullable = true)
    private String subQuestionId;

    @Column(name = "answerContent", columnDefinition = "TEXT", nullable = false)
    private String answerContent;

    @Column(name = "`order`", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int order = 0;

    @Column(name = "isCorrect", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isCorrect = false;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subQuestionId", referencedColumnName = "id", insertable = false, updatable = false)
    private SubQuestion subQuestion;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
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

    public SubQuestion getSubQuestion() {
        return subQuestion;
    }

    public void setSubQuestion(SubQuestion subQuestion) {
        this.subQuestion = subQuestion;
    }
}
