package com.practise.test.model.question;

public class ResultOfQuestion {
  private String id;
  private String question;
  private String answer;
  private String feedback;
  private Number point;
  private Boolean isRated;

  public ResultOfQuestion() {
  }

  public ResultOfQuestion(String id, String question, String answer, String feedback, Number point, Boolean isRated) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.feedback = feedback;
    this.point = point;
    this.isRated = isRated;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public Number getPoint() {
    return point;
  }

  public void setPoint(Number point) {
    this.point = point;
  }

  public Boolean getRated() {
    return isRated;
  }

  public void setRated(Boolean rated) {
    isRated = rated;
  }
}
