package com.practise.test.model.examQuestion;

import com.practise.test.model.question.QuestionOfExam;

public class ExamQuestionData {
    private String id;
    private String examId;
    private String questionId;
    private QuestionOfExam question;

    public ExamQuestionData() {
    }

    public ExamQuestionData(String id, String examId, String questionId, QuestionOfExam question) {
        this.id = id;
        this.examId = examId;
        this.questionId = questionId;
        this.question = question;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public QuestionOfExam getQuestion() {
        return question;
    }

    public void setQuestion(QuestionOfExam question) {
        this.question = question;
    }
}
