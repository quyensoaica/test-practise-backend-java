package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "ExamResultWritings")
public class ExamResultWriting {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "examQuestionId", length = 255, nullable = false)
    private String examQuestionId;

    @Column(name = "data", columnDefinition = "TEXT default ''", nullable = true)
    private String data;

    @Column(name = "point", nullable = false, columnDefinition = "FLOAT default 0")
    private Float point;

    @Column(name = "feedback", columnDefinition = "TEXT", nullable = true)
    private String feedback;

    @Column(name = "isRated", nullable = false, columnDefinition = "BOOLEAN default false")
    private Boolean isRated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examQuestionId", referencedColumnName = "id", insertable = false, updatable = false)
    private ExamQuestion examQuestion;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamQuestionId() {
        return examQuestionId;
    }

    public void setExamQuestionId(String examQuestionId) {
        this.examQuestionId = examQuestionId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean getIsRated() {
        return isRated;
    }

    public void setIsRated(Boolean isRated) {
        this.isRated = isRated;
    }

    public ExamQuestion getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(ExamQuestion examQuestion) {
        this.examQuestion = examQuestion;
    }
}
