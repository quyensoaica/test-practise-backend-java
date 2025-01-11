package com.practise.test.dto.exam;

import com.practise.test.model.exam.CurrentExam;
import com.practise.test.model.examQuestion.ExamQuestionData;
import com.practise.test.model.examSkillStatus.CurrentSkillStatus;

public class ContinueExamDTO {
    private CurrentExam exam;
    private CurrentSkillStatus skill;
    private ExamQuestionData[] questions;

    public ContinueExamDTO() {
    }

    public ContinueExamDTO(CurrentExam exam, CurrentSkillStatus skill, ExamQuestionData[] questions) {
        this.exam = exam;
        this.skill = skill;
        this.questions = questions;
    }

    public CurrentExam getExam() {
        return exam;
    }

    public void setExam(CurrentExam exam) {
        this.exam = exam;
    }

    public CurrentSkillStatus getSkill() {
        return skill;
    }

    public void setSkill(CurrentSkillStatus skill) {
        this.skill = skill;
    }

    public ExamQuestionData[] getQuestions() {
        return questions;
    }

    public void setQuestions(ExamQuestionData[] questions) {
        this.questions = questions;
    }
}
