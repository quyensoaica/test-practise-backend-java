package com.practise.test.model.question;

public class SubQuestionData {
    private String id;
    private String questionId;
    private String content;
    private Integer order;
    private String correctAnswer;
    private String selectedAnswerId;
    private SubQuestionAnswer[] answers;


    public SubQuestionData() {
    }

    public SubQuestionData(String id, String questionId, String content, Integer order, String correctAnswer, String selectedAnswerId, SubQuestionAnswer[] answers) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.order = order;
        this.correctAnswer = correctAnswer;
        this.selectedAnswerId = selectedAnswerId;
        this.answers = answers;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSelectedAnswerId() {
        return selectedAnswerId;
    }

    public void setSelectedAnswerId(String selectedAnswerId) {
        this.selectedAnswerId = selectedAnswerId;
    }

    public SubQuestionAnswer[] getAnswers() {
        return answers;
    }

    public void setAnswers(SubQuestionAnswer[] answers) {
        this.answers = answers;
    }
}
