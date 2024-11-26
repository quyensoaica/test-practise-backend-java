package com.practise.test.model.question;

public class QuestionDetail extends QuestionData {
    private SubQuestionData[] subQuestions;
    private String questionData;

    public QuestionDetail() {
    }

    public QuestionDetail(String id, String categoryId, String levelId, String skillId, String questionContent, String description, String questionNote, String attachedFile, boolean isDeleted, boolean isActive, SubQuestionData[] subQuestions, String questionData) {
        super(id, categoryId, levelId, skillId, questionContent, description, questionNote, attachedFile, isDeleted, isActive);
        this.subQuestions = subQuestions;
        this.questionData = questionData;
    }

    public SubQuestionData[] getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(SubQuestionData[] subQuestions) {
        this.subQuestions = subQuestions;
    }

    public String getQuestionData() {
        return questionData;
    }

    public void setQuestionData(String questionData) {
        this.questionData = questionData;
    }
}
