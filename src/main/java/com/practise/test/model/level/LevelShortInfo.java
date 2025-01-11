package com.practise.test.model.level;

public class LevelShortInfo {
    private String id;
    private String displayName;
    private String name;
    private String description;
    private Integer subQuestionNumber;

    public LevelShortInfo() {
    }

    public LevelShortInfo(String id, String displayName, String name, String description, Integer subQuestionNumber) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.description = description;
        this.subQuestionNumber = subQuestionNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSubQuestionNumber() {
        return subQuestionNumber;
    }

    public void setSubQuestionNumber(Integer subQuestionNumber) {
        this.subQuestionNumber = subQuestionNumber;
    }
}
