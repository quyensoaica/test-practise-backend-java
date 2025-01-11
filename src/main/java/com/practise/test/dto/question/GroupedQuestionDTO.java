package com.practise.test.dto.question;

import java.util.List;

public class GroupedQuestionDTO {
    private String levelId;
    private List<String> questionIds;

    public GroupedQuestionDTO() {
    }

    public GroupedQuestionDTO(String levelId, List<String> questionIds) {
        this.levelId = levelId;
        this.questionIds = questionIds;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }
}
