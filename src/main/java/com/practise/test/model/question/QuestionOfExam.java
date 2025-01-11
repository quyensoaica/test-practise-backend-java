package com.practise.test.model.question;

import com.practise.test.model.level.Levelinfo;
import com.practise.test.model.skill.SkillShortInfo;

public class QuestionOfExam {
    private String id;
    private String levelId;
    private String questionContent;
    private String questionNote;
    private String description;
    private String attachedFile;
    private SkillShortInfo skill;
    private Levelinfo level;
    private SubQuestionNotChoose[] subQuestions;

    public QuestionOfExam() {
    }

    public QuestionOfExam(String id, String levelId, String questionContent, String questionNote, String description, String attachedFile, SkillShortInfo skill, Levelinfo level, SubQuestionNotChoose[] subQuestions) {
        this.id = id;
        this.levelId = levelId;
        this.questionContent = questionContent;
        this.questionNote = questionNote;
        this.description = description;
        this.attachedFile = attachedFile;
        this.skill = skill;
        this.level = level;
        this.subQuestions = subQuestions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionNote() {
        return questionNote;
    }

    public void setQuestionNote(String questionNote) {
        this.questionNote = questionNote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    public SkillShortInfo getSkill() {
        return skill;
    }

    public void setSkill(SkillShortInfo skill) {
        this.skill = skill;
    }

    public Levelinfo getLevel() {
        return level;
    }

    public void setLevel(Levelinfo level) {
        this.level = level;
    }

    public SubQuestionNotChoose[] getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(SubQuestionNotChoose[] subQuestions) {
        this.subQuestions = subQuestions;
    }
}
