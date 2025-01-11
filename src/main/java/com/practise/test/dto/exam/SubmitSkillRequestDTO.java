package com.practise.test.dto.exam;

import com.practise.test.dto.question.QuestionDetailResponseDTO;

public class SubmitSkillRequestDTO {
    private String skillId;
    private QuestionDetailResponseDTO[] questions;

    public SubmitSkillRequestDTO() {
    }

    public SubmitSkillRequestDTO(String skillId, QuestionDetailResponseDTO[] questions) {
        this.skillId = skillId;
        this.questions = questions;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public QuestionDetailResponseDTO[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionDetailResponseDTO[] questions) {
        this.questions = questions;
    }
}
