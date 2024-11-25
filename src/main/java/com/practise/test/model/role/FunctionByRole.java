package com.practise.test.model.role;

public class FunctionByRole {
    private String id;
    private String name;
    private String displayName;
    private String functionLink;

    public FunctionByRole() {
    }

    public FunctionByRole(String id, String name, String displayName, String functionLink) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.functionLink = functionLink;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFunctionLink() {
        return functionLink;
    }

    public void setFunctionLink(String functionLink) {
        this.functionLink = functionLink;
    }
}
