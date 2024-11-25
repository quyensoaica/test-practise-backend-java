package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "Questions")
public class Question {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "skillId", length = 255, nullable = false, insertable = false, updatable = false)
    private String skillId;

    @Column(name = "levelId", length = 255, nullable = false, insertable = false, updatable = false)
    private String levelId;

    @Column(name = "categoryId", length = 255, nullable = false, insertable = false, updatable = false)
    private String categoryId;

    @Column(name = "questionContent", columnDefinition = "text", nullable = false)
    private String questionContent;

    @Column(name = "questionNote", columnDefinition = "text", nullable = true)
    private String questionNote;

    @Column(name = "questionImage", length = 1000, nullable = true)
    private String questionImage;

    @Column(name = "description", columnDefinition = "text", nullable = true)
    private String description;

    @Column(name = "attachedFile", length = 1000, nullable = true)
    private String attachedFile;

    @Column(name = "isDeleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "isActive", nullable = false, columnDefinition = "boolean default false")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "createdBy", length = 255, nullable = true)
    private String createdBy;

    @Column(name = "updatedBy", length = 255, nullable = true)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "levelId", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_Question_Level"))
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_Question_Category"))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skillId", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_Question_Skill"))
    private Skill skill;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubQuestion> subQuestions;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public List<SubQuestion> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(List<SubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }
}
