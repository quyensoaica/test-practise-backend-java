package com.practise.test.model.question;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubQuestionAnswer {
    private String id;
    private String subQuestionId;
    private String answerContent;
    private Integer order;
    @JsonProperty("isCorrect")
    private boolean isCorrect;

    public SubQuestionAnswer() {
    }

    public SubQuestionAnswer(String id, String subQuestionId, String answerContent, Integer order, boolean isCorrect) {
        this.id = id;
        this.subQuestionId = subQuestionId;
        this.answerContent = answerContent;
        this.order = order;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    @JsonProperty("isCorrect")
    public boolean isCorrect() {
        return isCorrect;
    }

    @JsonProperty("isCorrect")
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
