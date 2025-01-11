package com.practise.test.model.question;

public class AnswerNotChoose {
    private String id;
    private String answerContent;
    private Integer order;

    public AnswerNotChoose() {
    }

    public AnswerNotChoose(String id, String answerContent, Integer order) {
        this.id = id;
        this.answerContent = answerContent;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
