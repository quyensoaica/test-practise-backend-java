package com.practise.test.dto.question;

import com.practise.test.model.category.CategoryShortInfo;
import com.practise.test.model.level.LevelShortInfo;
import com.practise.test.model.question.QuestionData;
import com.practise.test.model.skill.SkillShortInfo;

public class QuestionResponseDTO extends QuestionData {
    private CategoryShortInfo category;
    private LevelShortInfo level;
    private SkillShortInfo skill;

    public QuestionResponseDTO() {
    }

    public QuestionResponseDTO(CategoryShortInfo category, LevelShortInfo level, SkillShortInfo skill) {
        this.category = category;
        this.level = level;
        this.skill = skill;
    }

    public QuestionResponseDTO(
            String id,
            String categoryId,
            String levelId,
            String skillId,
            String questionContent,
            String description,
            String questionNote,
            String attachedFile,
            boolean isDeleted,
            boolean isActive,
            CategoryShortInfo category,
            LevelShortInfo level,
            SkillShortInfo skill
    ) {
        super(id, categoryId, levelId, skillId, questionContent, description, questionNote, attachedFile, isDeleted, isActive);
        this.category = category;
        this.level = level;
        this.skill = skill;
    }

    public CategoryShortInfo getCategory() {
        return category;
    }

    public void setCategory(CategoryShortInfo category) {
        this.category = category;
    }

    public LevelShortInfo getLevel() {
        return level;
    }

    public void setLevel(LevelShortInfo level) {
        this.level = level;
    }

    public SkillShortInfo getSkill() {
        return skill;
    }

    public void setSkill(SkillShortInfo skill) {
        this.skill = skill;
    }
}
