package com.practise.test.model.skill;

public class SkillData {
    private String id;
    private String name;
    private String description;
    private String displayName;
    private String image;
    private Integer order;
    private Integer expiredTime;

    public SkillData() {
    }

    public SkillData(String id, String name, String displayName, String description, String image, int order, int expiredTime) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.order = order;
        this.expiredTime = expiredTime;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }
}
