package com.practise.test.dto.skill;

public class UpdateSkillRequestDTO {
    private String skillId;
    private String image;
    private String description;

    public UpdateSkillRequestDTO() {
    }

    public UpdateSkillRequestDTO(String skillId, String image, String description) {
        this.skillId = skillId;
        this.image = image;
        this.description = description;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
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
}
