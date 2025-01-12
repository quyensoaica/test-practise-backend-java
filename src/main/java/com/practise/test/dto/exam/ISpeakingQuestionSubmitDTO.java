package com.practise.test.dto.exam;

public class ISpeakingQuestionSubmitDTO {
  private String questionId;
  private String answer;
  private  String skillId;
  private String levelId;

  public ISpeakingQuestionSubmitDTO() {
  }

  public ISpeakingQuestionSubmitDTO(String questionId, String answer, String skillId, String levelId) {
    this.questionId = questionId;
    this.answer = answer;
    this.skillId = skillId;
    this.levelId = levelId;
  }

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getSkillId() {
    return skillId;
  }

  public void setSkillId(String skillId) {
    this.skillId = skillId;
  }

  public String getLevelId() {
    return levelId;
  }

  public void setLevelId(String levelId) {
    this.levelId = levelId;
  }
}
