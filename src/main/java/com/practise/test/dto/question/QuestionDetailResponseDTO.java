package com.practise.test.dto.question;

import com.practise.test.model.category.CategoryShortInfo;
import com.practise.test.model.level.LevelShortInfo;
import com.practise.test.model.question.QuestionData;
import com.practise.test.model.question.SubQuestionData;
import com.practise.test.model.skill.SkillShortInfo;

public class QuestionDetailResponseDTO extends QuestionResponseDTO {
    private SubQuestionData[] subQuestions;
    private String questionData;

    public QuestionDetailResponseDTO() {
    }

    public QuestionDetailResponseDTO(CategoryShortInfo category, LevelShortInfo level, SkillShortInfo skill, SubQuestionData[] subQuestions) {
        super(category, level, skill);
        this.subQuestions = subQuestions;
    }

    public QuestionDetailResponseDTO(
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
            SkillShortInfo skill,
            SubQuestionData[] subQuestions
    ) {
        super(id, categoryId, levelId, skillId, questionContent, description, questionNote, attachedFile, isDeleted, isActive, category, level, skill);
        this.subQuestions = subQuestions;
    }

    public QuestionDetailResponseDTO(String id,
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
         SkillShortInfo skill,
         SubQuestionData[] subQuestions,
         String questionData
    ) {
        super(id, categoryId, levelId, skillId, questionContent, description, questionNote, attachedFile, isDeleted, isActive, category, level, skill);
        this.subQuestions = subQuestions;
        this.questionData = questionData;
    }

    public SubQuestionData[] getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(SubQuestionData[] subQuestions) {
        this.subQuestions = subQuestions;
    }

    public String getQuestionData() {
        return questionData;
    }

    public void setQuestionData(String questionData) {
        this.questionData = questionData;
    }
}
