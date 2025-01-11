package com.practise.test.model.question;

import com.practise.test.dto.question.QuestionDetailResponseDTO;

public class QuestionResult {
  private String id;
  private String questionId;
  private String examId;
  private QuestionDetailResponseDTO question;
  private ResultOfQuestion[] results;

  public QuestionResult() {
  }

  public QuestionResult(String id, String questionId, String examId, QuestionDetailResponseDTO question, ResultOfQuestion[] results) {
    this.id = id;
    this.questionId = questionId;
    this.examId = examId;
    this.question = question;
    this.results = results;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public String getExamId() {
    return examId;
  }

  public void setExamId(String examId) {
    this.examId = examId;
  }

  public QuestionDetailResponseDTO getQuestion() {
    return question;
  }

  public void setQuestion(QuestionDetailResponseDTO question) {
    this.question = question;
  }

  public ResultOfQuestion[] getResults() {
    return results;
  }

  public void setResults(ResultOfQuestion[] results) {
    this.results = results;
  }
}
