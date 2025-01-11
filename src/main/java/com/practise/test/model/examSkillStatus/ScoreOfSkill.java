package com.practise.test.model.examSkillStatus;

public class ScoreOfSkill {
    private String skillId;
    private Integer score;
    private Integer totalQuestion;
    private String status;
    private Integer order;

    public ScoreOfSkill() {
    }

    public ScoreOfSkill(String skillId, Integer score, Integer totalQuestion, String status, Integer order) {
        this.skillId = skillId;
        this.score = score;
        this.totalQuestion = totalQuestion;
        this.status = status;
        this.order = order;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
