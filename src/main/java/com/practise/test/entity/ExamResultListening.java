package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "ExamResultListenings")
public class ExamResultListening {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "examQuestionId", length = 255, nullable = false, insertable = false, updatable = false)
    private String examQuestionId;

    @Column(name = "subQuestionId", length = 255, nullable = false, insertable = false, updatable = false)
    private String subQuestionId;

    @Column(name = "answerId", length = 255, nullable = false, insertable = false, updatable = false)
    private String answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examQuestionId", referencedColumnName = "id", insertable = false, updatable = false)
    private ExamQuestion examQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subQuestionId", referencedColumnName = "id", insertable = false, updatable = false)
    private SubQuestion subQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerId", referencedColumnName = "id", insertable = false, updatable = false)
    private Answer answer;

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

    public String getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public ExamQuestion getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(ExamQuestion examQuestion) {
        this.examQuestion = examQuestion;
    }

    public SubQuestion getSubQuestion() {
        return subQuestion;
    }

    public void setSubQuestion(SubQuestion subQuestion) {
        this.subQuestion = subQuestion;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
