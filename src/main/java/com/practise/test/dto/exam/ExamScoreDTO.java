package com.practise.test.dto.exam;

import com.practise.test.model.examSkillStatus.ScoreOfSkill;

public class ExamScoreDTO {
    private String id;
    private String examCode;
    private String startTime;
    private String endTime;
    private ScoreOfSkill[] examSkillStatuses;

    public ExamScoreDTO() {
    }

    public ExamScoreDTO(String id, String examCode, String startTime, String endTime, ScoreOfSkill[] examSkillStatuses) {
        this.id = id;
        this.examCode = examCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.examSkillStatuses = examSkillStatuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ScoreOfSkill[] getExamSkillStatuses() {
        return examSkillStatuses;
    }

    public void setExamSkillStatuses(
        ScoreOfSkill[] examSkillStatuses) {
        this.examSkillStatuses = examSkillStatuses;
    }
}
