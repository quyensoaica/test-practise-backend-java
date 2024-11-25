package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "ExamQuestions")
public class ExamQuestion {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "examId", length = 255, nullable = false, insertable = false, updatable = false)
    private String examId;

    @Column(name = "questionId", length = 255, nullable = false, insertable = false, updatable = false)
    private String questionId;

    @Column(name = "levelId", length = 255, nullable = true, insertable = false, updatable = false)
    private String levelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examId", referencedColumnName = "id", insertable = false, updatable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId", referencedColumnName = "id", insertable = false, updatable = false)
    private Question question;

    @OneToMany(mappedBy = "examQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResultWriting> examResultWritings;

    @OneToMany(mappedBy = "examQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResultListening> examResultListenings;

    @OneToMany(mappedBy = "examQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResultSpeaking> examResultSpeakings;

    @OneToMany(mappedBy = "examQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamResultReading> examResultReadings;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<ExamResultWriting> getExamResultWritings() {
        return examResultWritings;
    }

    public void setExamResultWritings(List<ExamResultWriting> examResultWritings) {
        this.examResultWritings = examResultWritings;
    }

    public List<ExamResultListening> getExamResultListenings() {
        return examResultListenings;
    }

    public void setExamResultListenings(List<ExamResultListening> examResultListenings) {
        this.examResultListenings = examResultListenings;
    }

    public List<ExamResultSpeaking> getExamResultSpeakings() {
        return examResultSpeakings;
    }

    public void setExamResultSpeakings(List<ExamResultSpeaking> examResultSpeakings) {
        this.examResultSpeakings = examResultSpeakings;
    }

    public List<ExamResultReading> getExamResultReadings() {
        return examResultReadings;
    }

    public void setExamResultReadings(List<ExamResultReading> examResultReadings) {
        this.examResultReadings = examResultReadings;
    }
}
