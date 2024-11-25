package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "ExamSkillStatuses")
public class ExamSkillStatus {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "examId", length = 255, nullable = false, insertable = false, updatable = false)
    private String examId;

    @Column(name = "skillId", length = 255, nullable = false, insertable = false, updatable = false)
    private String skillId;

    @Column(name = "startTime", length = 255, nullable = true)
    private String startTime;

    @Column(name = "endTime", length = 255, nullable = true)
    private String endTime;

    @Column(name = "status", length = 255, nullable = true)
    private String status;

    @Column(name = "`order`", nullable = false, columnDefinition = "integer default 0")
    private int order;

    @Column(name = "score", nullable = false, columnDefinition = "float default 0")
    private float score;

    @Column(name = "totalQuestion", nullable = false, columnDefinition = "integer default 1")
    private int totalQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examId", referencedColumnName = "id", insertable = false, updatable = false)
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skillId", referencedColumnName = "id", insertable = false, updatable = false)
    private Skill skill;

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

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
