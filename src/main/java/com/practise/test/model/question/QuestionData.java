package com.practise.test.model.question;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionData {
    private String id;
    private String categoryId;
    private String levelId;
    private String skillId;
    private String questionContent;
    private String description;
    private String questionNote;
    private String attachedFile;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    @JsonProperty("isActive")
    private boolean isActive;

    public QuestionData() {
    }

    public QuestionData(String id, String categoryId, String levelId, String skillId, String questionContent, String description, String questionNote, String attachedFile, boolean isDeleted, boolean isActive) {
        this.id = id;
        this.categoryId = categoryId;
        this.levelId = levelId;
        this.skillId = skillId;
        this.questionContent = questionContent;
        this.description = description;
        this.questionNote = questionNote;
        this.attachedFile = attachedFile;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestionNote() {
        return questionNote;
    }

    public void setQuestionNote(String questionNote) {
        this.questionNote = questionNote;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    @JsonProperty("isDeleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @JsonProperty("isActive")
    public boolean isActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setActive(boolean active) {
        isActive = active;
    }
}
