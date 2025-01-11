package com.practise.test.model.skill;

public class SkillShortInfo {
    private String id;
    private String displayName;
    private String name;

    public SkillShortInfo() {
    }

    public SkillShortInfo(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public SkillShortInfo(String id, String displayName, String name) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }

    // Getters and Setters

    public String getName() {
        return displayName;
    }

    public void setName(String displayName) {
        this.displayName = displayName;
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
}
