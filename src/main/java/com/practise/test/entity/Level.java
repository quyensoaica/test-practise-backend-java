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
@Table(name = "Levels")
public class Level {

    @Id
    @Column(name = "id", length = 255, nullable = false)
    private String id;

    @Column(name = "skillId", length = 255, nullable = false)
    private String skillId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "displayName", length = 255, nullable = false)
    private String displayName;

    @Column(name = "description", columnDefinition = "text", nullable = true)
    private String description;

    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @Column(name = "subQuestionNumber", nullable = false, columnDefinition = "integer default 0")
    private int subQuestionNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skillId", referencedColumnName = "id", insertable = false, updatable = false)
    private Skill skill;

    @OneToMany(mappedBy = "level",fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(mappedBy = "level",fetch = FetchType.EAGER)
    private List<Question> questions;

    public Level() {
    }

    public Level(String id, String skillId, String name, String displayName, String description, String image, int subQuestionNumber) {
        this.id = id;
        this.skillId = skillId;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.image = image;
        this.subQuestionNumber = subQuestionNumber;
    }

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

    public int getSubQuestionNumber() {
        return subQuestionNumber;
    }

    public void setSubQuestionNumber(int subQuestionNumber) {
        this.subQuestionNumber = subQuestionNumber;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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
}
