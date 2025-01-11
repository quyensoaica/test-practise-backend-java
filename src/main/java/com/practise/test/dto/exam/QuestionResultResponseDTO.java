package com.practise.test.dto.exam;

import com.practise.test.model.exam.CurrentExam;
import com.practise.test.model.examSkillStatus.CurrentSkillStatus;
import com.practise.test.model.question.QuestionResult;

public class QuestionResultResponseDTO {
  private CurrentExam exam;
  private QuestionResult[] questions;
  private CurrentSkillStatus skill;

  public QuestionResultResponseDTO() {
  }

  public QuestionResultResponseDTO(CurrentExam exam, QuestionResult[] questions, CurrentSkillStatus skill) {
    this.exam = exam;
    this.questions = questions;
    this.skill = skill;
  }

  public CurrentExam getExam() {
    return exam;
  }

  public void setExam(CurrentExam exam) {
    this.exam = exam;
  }

  public QuestionResult[] getQuestions() {
    return questions;
  }

  public void setQuestions(QuestionResult[] questions) {
    this.questions = questions;
  }

  public CurrentSkillStatus getSkill() {
    return skill;
  }

  public void setSkill(CurrentSkillStatus skill) {
    this.skill = skill;
  }
}
