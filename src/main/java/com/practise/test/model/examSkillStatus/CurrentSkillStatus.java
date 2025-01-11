package com.practise.test.model.examSkillStatus;

import com.practise.test.model.skill.SkillExprired;

public class CurrentSkillStatus {
    private String id;
    private String examId;
    private String skillId;
    private String startTime;
    private String endTime;
    private String status;
    private Integer order;
    private SkillExprired skill;

    public CurrentSkillStatus() {
    }

    public CurrentSkillStatus(String id, String examId, String skillId, String startTime, String endTime, String status, Integer order, SkillExprired skill) {
        this.id = id;
        this.examId = examId;
        this.skillId = skillId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.order = order;
        this.skill = skill;
    }

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public SkillExprired getSkill() {
        return skill;
    }

    public void setSkill(SkillExprired skill) {
        this.skill = skill;
    }
}
