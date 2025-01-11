package com.practise.test.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryInfo {
    private String id;
    private String name;
    private String description;
    private String image;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("isActive")
    private boolean isActive;

    private String skillId;
    private String levelId;

    public CategoryInfo() {
    }

    public CategoryInfo(String id, String name, String description, String image, boolean isDeleted, boolean isActive, String skillId, String levelId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.skillId = skillId;
        this.levelId = levelId;
    }

    // Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
