package com.practise.test.dto.level;

public class UpdateLevelRequestDTO {
    private String levelId;
    private String image;
    private String description;
    private Integer subQuestionNumber;

    public UpdateLevelRequestDTO() {
    }

    public UpdateLevelRequestDTO(String levelId, String image, String description, Integer subQuestionNumber) {
        this.levelId = levelId;
        this.image = image;
        this.description = description;
        this.subQuestionNumber = subQuestionNumber;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
