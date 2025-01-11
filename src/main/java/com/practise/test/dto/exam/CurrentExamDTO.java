package com.practise.test.dto.exam;

import com.practise.test.entity.Exam;
import com.practise.test.model.exam.CurrentExam;
import com.practise.test.model.examSkillStatus.CurrentSkillStatus;

public class CurrentExamDTO {
    private CurrentExam exam;
    private CurrentSkillStatus currentSkill;

    public CurrentExamDTO(CurrentExam exam, CurrentSkillStatus currentSkill) {
        this.exam = exam;
        this.currentSkill = currentSkill;
    }

    public CurrentExamDTO() {
    }

    public CurrentExam getExam() {
        return exam;
    }

    public void setExam(CurrentExam exam) {
        this.exam = exam;
    }

    public CurrentSkillStatus getCurrentSkill() {
        return currentSkill;
    }

    public void setCurrentSkill(CurrentSkillStatus currentSkill) {
        this.currentSkill = currentSkill;
    }
}
