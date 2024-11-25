package com.practise.test.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "Skills")
public class Skill {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "displayName", length = 1000, nullable = false)
    private String displayName;

    @Column(name = "description", columnDefinition = "text", nullable = true)
    private String description;

    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @Column(name = "`order`", nullable = false, columnDefinition = "integer default 0")
    private int order;

    @Column(name = "expiredTime", nullable = false, columnDefinition = "integer default 0")
    private int expiredTime;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<Level> levels;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<ExamSkillStatus> examSkillStatuses;


    public Skill() {
    }

    public Skill(String id, String name, String displayName, String description, String image, int order, int expiredTime) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.order = order;
        this.expiredTime = expiredTime;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<ExamSkillStatus> getExamSkillStatuses() {
        return examSkillStatuses;
    }

    public void setExamSkillStatuses(List<ExamSkillStatus> examSkillStatuses) {
        this.examSkillStatuses = examSkillStatuses;
    }
}
