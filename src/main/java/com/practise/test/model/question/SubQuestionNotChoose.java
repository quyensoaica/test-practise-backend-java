package com.practise.test.model.question;

public class SubQuestionNotChoose {
    private String id;
    private String content;
    private Integer order;
    private String selectedAnswer;
    private AnswerNotChoose[] answers;

    public SubQuestionNotChoose() {
    }

    public SubQuestionNotChoose(String id, String content, Integer order, String selectedAnswer, AnswerNotChoose[] answers) {
        this.id = id;
        this.content = content;
        this.order = order;
        this.selectedAnswer = selectedAnswer;
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public AnswerNotChoose[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerNotChoose[] answers) {
        this.answers = answers;
    }
}
