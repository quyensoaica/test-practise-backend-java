package com.practise.test.model.level;

public class LevelShortInfo {
    private String id;
    private String displayName;

    public LevelShortInfo() {
    }

    public LevelShortInfo(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
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
}
