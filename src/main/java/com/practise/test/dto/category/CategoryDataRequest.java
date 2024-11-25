package com.practise.test.dto.category;

public class CategoryDataRequest {
    private String id;
    private String name;
    private String description;
    private String image;
    private String skillId;
    private String levelId;

    public CategoryDataRequest() {
    }

    public CategoryDataRequest(String id, String name, String description, String image, String skillId, String levelId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.skillId = skillId;
        this.levelId = levelId;
    }

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
