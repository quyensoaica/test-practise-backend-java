package com.practise.test.model.level;

public class Levelinfo {
    private String id;
    private String skillId;
    private String name;
    private String displayName;
    private String description;
    private String image;
    private int subQuestionNumber;

    public Levelinfo() {
    }

    public Levelinfo(String id, String skillId, String name, String displayName, String description, String image, int subQuestionNumber) {
        this.id = id;
        this.skillId = skillId;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.subQuestionNumber = subQuestionNumber;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSubQuestionNumber() {
        return subQuestionNumber;
    }

    public void setSubQuestionNumber(int subQuestionNumber) {
        this.subQuestionNumber = subQuestionNumber;
    }
}
