package com.practise.test.model.skill;

public class SkillExprired {
    private String name;
    private int expiredTime;

    public SkillExprired() {
    }

    public SkillExprired(String name, int expiredTime) {
        this.name = name;
        this.expiredTime = expiredTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }
}
