package com.practise.test.model.category;

public class CategoryShortInfo {
    private String id;
    private String name;

    public CategoryShortInfo() {
    }

    public CategoryShortInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
