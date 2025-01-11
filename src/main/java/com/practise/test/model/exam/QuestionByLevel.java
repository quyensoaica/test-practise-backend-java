package com.practise.test.model.exam;

public class QuestionByLevel {
    private String levelId;
    private String questionId;

    public QuestionByLevel() {
    }

    public QuestionByLevel(String levelId, String questionId) {
        this.levelId = levelId;
        this.questionId = questionId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
