package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "Categories")
public class Category {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @Column(name = "isDeleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @Column(name = "isActive", nullable = true, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

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

    @Column(name = "skillId", length = 255, nullable = false)
    private String skillId;

    @Column(name = "levelId", length = 255, nullable = false)
    private String levelId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "skillId", referencedColumnName = "id", insertable = false, updatable = false)
    private Skill skill;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "levelId", referencedColumnName = "id", insertable = false, updatable = false)
    private Level level;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;


    public Category() {
    }

    public Category(String id, String name, String description, String image, boolean isDeleted, Boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, String skillId, String levelId, Skill skill, Level level, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.skillId = skillId;
        this.levelId = levelId;
        this.skill = skill;
        this.level = level;
        this.questions = questions;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
